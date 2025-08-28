package me.dio.domain.repository;

import me.dio.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByAccountNumber(String accountNumber);
    
    @Query("SELECT u FROM tb_user u WHERE u.account.number = :accountNumber")
    Optional<User> findByAccountNumber(@Param("accountNumber") String accountNumber);
    
    @Query("SELECT u FROM tb_user u WHERE u.name LIKE %:name%")
    Optional<User> findByNameContaining(@Param("name") String name);
}
