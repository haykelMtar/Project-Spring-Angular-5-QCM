package org.exam.dao;

import org.exam.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findUserByUsername(String username);

	@Query("select u from User u where u.nom like %:x%")
	public Page<User> getUser(@Param("x") String nom, Pageable pageable);
	
	
	public User findByUsername(String username);
}
