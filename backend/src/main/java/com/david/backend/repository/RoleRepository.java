package com.david.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
