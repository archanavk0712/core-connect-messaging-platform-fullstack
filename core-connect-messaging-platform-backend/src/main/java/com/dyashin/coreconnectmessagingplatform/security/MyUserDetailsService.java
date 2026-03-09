package com.dyashin.coreconnectmessagingplatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dyashin.coreconnectmessagingplatform.entity.User;
import com.dyashin.coreconnectmessagingplatform.exception.MessageException;
import com.dyashin.coreconnectmessagingplatform.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user=userRepo.findByEmail(email).orElseThrow(() -> new MessageException("User not found"));
		
		return new UserPrincipal(user);
	}

}
