package me.dio.service;

import me.dio.dto.TransferDto;
import me.dio.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto findById(Long id);
    
    Page<UserDto> findAll(Pageable pageable);
    
    UserDto findByAccountNumber(String accountNumber);

    UserDto create(UserDto userToCreate);
    
    UserDto update(Long id, UserDto userToUpdate);
    
    void delete(Long id);
    
    UserDto transfer(TransferDto transferDto);
}
