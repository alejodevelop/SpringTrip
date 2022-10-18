package io.getarrays.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.getarrays.userservice.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
