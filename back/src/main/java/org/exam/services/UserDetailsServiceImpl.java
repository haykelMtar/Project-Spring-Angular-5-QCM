package org.exam.services;

import java.util.ArrayList;
import java.util.Collection;

import org.exam.dao.UserRepository;
import org.exam.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	
	private UserRepository userRepository ;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		
		if(user == null) throw new UsernameNotFoundException(username); 
		
		Collection<GrantedAuthority> autorities = new ArrayList<>();
		user.getRoles().forEach(r->{
			autorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),autorities);
	}

}
