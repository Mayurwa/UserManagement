package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.exception.UserAlreadyPresentException;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;

@Service
@Transactional
//@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleRepo roleRepo;

/*	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findUserByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("user not found");
		} else {
			user.setLastLoginDateDisplay(user.getLastLoginDate());
			user.setLastLoginDate(new Date());
			userRepo.save(user);
			UserPrinciple userPrinciple = new UserPrinciple(user);
			return userPrinciple;
		}

	}*/

	@Override
	public void register(User user) throws UserAlreadyPresentException, RoleNotFoundException {

		User dbUser = userRepo.findByuserName(user.getUserName());
		if (Objects.nonNull(dbUser) && null != dbUser.getUserName()) {
			if (dbUser.getUserName().equals(user.getUserName())
					|| dbUser.getEmail().equalsIgnoreCase(user.getEmail())) {
				throw new UserAlreadyPresentException("User Already Present");
			}
		}

		user.setPassword(encoder.encode(user.getPassword()));
		user.setLastLoginDate(new Date());
		List<Role> userRoles = user.getRoles();
		
		if(null!= userRoles && !userRoles.isEmpty())
		
		{
			user.setRoles(userRoles);
		}
         userRepo.save(user);

		/*
		 * if(userRoles==null) {
		 * 
		 * 
		 * Role userRole=roleRepo.findByName(EROLE.USER_ROLE).orElseThrow(()-> new
		 * RoleNotFoundException("role is not Defined"));
		 * 
		 * if(Objects.nonNull(userRole)) { roleList.add(userRole.getRoleValue()); } else
		 * { userRoles.forEach(role-> { switch(role) {
		 * 
		 * } }
		 * 
		 * 
		 * );
		 * 
		 * }
		 * 
		 */

	}

	@Override
	public User loadByuserName(String userName) {
		// TODO Auto-generated method stub
		User user = null;
		if(null!= userName && !userName.isEmpty())
		{
			user= userRepo.findByuserName(userName);
			if(user== null)
			{
				 throw new UsernameNotFoundException("User Not found");
			}
			
		}
		return user;
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = null;
		if(null!= username && username.isEmpty())
		{
			user= userRepo.findByuserName(username);
			if(user!= null)
			{
				 if(username.equals(user.getUserName()))
				 {
					 return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), null);
					 
				 }
			}
			else
			{
				throw new UsernameNotFoundException("user not found");
			}
			
		}
		
		return null;
	}

	

}
