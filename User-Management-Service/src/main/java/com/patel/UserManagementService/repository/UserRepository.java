package com.patel.UserManagementService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patel.UserManagementService.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String username);

}
