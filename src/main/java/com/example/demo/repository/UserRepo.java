package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	//public User findUserByUsername(String username);

	public User findByEmail(String email);


	public User findByuserName(String userName);

}
