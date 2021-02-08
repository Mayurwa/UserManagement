package com.example.demo.service;

import javax.management.relation.RoleNotFoundException;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.exception.UserAlreadyPresentException;
@Service
public interface UserService {

	/**
	 * @param user
	 * calling below method to register the user in database 
	 * Also check weather the user is present in the db or not
	 * @throws UserAlreadyPresentException 
	 * @throws RoleNotFoundException 
	 */
	public void register(User user) throws UserAlreadyPresentException, RoleNotFoundException;

	public User loadByuserName(String userName);

}
