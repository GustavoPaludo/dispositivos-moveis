package com.mobile.core.user.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserBO extends JpaRepository<User, Long> {

	public User findByEmailAndPassword(String email, String password);

	public User findById(Integer id);
}
