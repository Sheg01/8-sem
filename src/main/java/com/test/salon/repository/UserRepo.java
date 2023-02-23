package com.test.salon.repository;

import com.test.salon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findById(Long id);
}