package org.exam.services;

import java.util.List;

import org.exam.dao.RoleRepository;
import org.exam.dao.UserRepository;
import org.exam.entities.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
@CrossOrigin("*")
public class UserRestService {

	
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private RoleRepository roleRepository;

	@Autowired
	public UserRestService(
			UserRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}
	

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User saveUser(@RequestBody User u) {
		String hashPW=bCryptPasswordEncoder.encode(u.getPassword());
		u.setPassword(hashPW);
		return userRepository.save(u);
	}


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Page<User> getUsers(
			@RequestParam(name="nom",defaultValue="") String nom,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size) {
		return userRepository.getUser(nom, new PageRequest(page,size));
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		return userRepository.findById(id).get();
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public boolean updateUser(@PathVariable Long id, @RequestBody User u) {
		return true ;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable Long id) {
		return true ;
		}

}
