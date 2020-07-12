package com.sflpro.cafe.repository;

import com.sflpro.cafe.domain.User;
import com.sflpro.cafe.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByType(UserType type);

    Optional<User> findByEmail(String email);
}
