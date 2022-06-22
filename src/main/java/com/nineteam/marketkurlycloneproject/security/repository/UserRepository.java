package com.nineteam.marketkurlycloneproject.security.repository;

import com.nineteam.marketkurlycloneproject.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLoginId(String loginId);
}
