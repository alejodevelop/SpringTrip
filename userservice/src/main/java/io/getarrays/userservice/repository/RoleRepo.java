package io.getarrays.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.getarrays.userservice.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String username);
}
