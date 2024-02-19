package com.app.repository;

import com.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    default Optional<Role> byName(String roleName) {
        return findByNameIgnoreCase(roleName);
    }

    Optional<Role> findByNameIgnoreCase(String name);
}
