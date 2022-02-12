package com.example.codecoverage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.codecoverage.modal.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
}