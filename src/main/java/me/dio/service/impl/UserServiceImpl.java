package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.dto.TransferDto;
import me.dio.dto.UserDto;
import me.dio.exception.BusinessException;
import me.dio.exception.InsufficientFundsException;
import me.dio.exception.ResourceNotFoundException;
import me.dio.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByAccountNumber(String accountNumber) {
        User user = userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com conta " + accountNumber));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto create(UserDto userToCreate) {
        User user = modelMapper.map(userToCreate, User.class);
        
        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new BusinessException("Já existe uma conta com o número: " + user.getAccount().getNumber());
        }
        
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserDto userToUpdate) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id));

        // Verifica se o número da conta não está sendo usado por outro usuário
        if (!existingUser.getAccount().getNumber().equals(userToUpdate.getAccount().getNumber()) &&
            userRepository.existsByAccountNumber(userToUpdate.getAccount().getNumber())) {
            throw new BusinessException("Já existe uma conta com o número: " + userToUpdate.getAccount().getNumber());
        }

        // Atualiza os campos do usuário existente
        existingUser.setName(userToUpdate.getName());
        existingUser.getAccount().setNumber(userToUpdate.getAccount().getNumber());
        existingUser.getAccount().setAgency(userToUpdate.getAccount().getAgency());
        existingUser.getAccount().setBalance(userToUpdate.getAccount().getBalance());
        existingUser.getAccount().setLimit(userToUpdate.getAccount().getLimit());
        
        if (userToUpdate.getCard() != null) {
            if (existingUser.getCard() != null) {
                existingUser.getCard().setNumber(userToUpdate.getCard().getNumber());
                existingUser.getCard().setLimit(userToUpdate.getCard().getLimit());
            } else {
                existingUser.setCard(modelMapper.map(userToUpdate.getCard(), me.dio.domain.model.Card.class));
            }
        }

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id));
        
        // Verifica se a conta tem saldo positivo
        if (user.getAccount().getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new BusinessException("Não é possível excluir uma conta com saldo positivo. Saldo atual: R$ " + user.getAccount().getBalance());
        }
        
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public UserDto transfer(TransferDto transferDto) {
        // Busca conta de origem
        User fromUser = userRepository.findByAccountNumber(transferDto.getFromAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Conta de origem não encontrada: " + transferDto.getFromAccount()));

        // Busca conta de destino
        User toUser = userRepository.findByAccountNumber(transferDto.getToAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Conta de destino não encontrada: " + transferDto.getToAccount()));

        // Verifica se as contas são diferentes
        if (fromUser.getId().equals(toUser.getId())) {
            throw new BusinessException("Não é possível transferir para a mesma conta");
        }

        // Calcula saldo disponível (saldo + limite)
        BigDecimal availableBalance = fromUser.getAccount().getBalance().add(fromUser.getAccount().getLimit());
        
        // Verifica se há saldo suficiente
        if (availableBalance.compareTo(transferDto.getAmount()) < 0) {
            throw new InsufficientFundsException(availableBalance, transferDto.getAmount());
        }

        // Realiza a transferência
        fromUser.getAccount().setBalance(fromUser.getAccount().getBalance().subtract(transferDto.getAmount()));
        toUser.getAccount().setBalance(toUser.getAccount().getBalance().add(transferDto.getAmount()));

        // Salva as alterações
        userRepository.save(fromUser);
        userRepository.save(toUser);

        return modelMapper.map(fromUser, UserDto.class);
    }
}
