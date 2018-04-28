package org.exam.dao;

import org.exam.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findByRoleName(String rolename);

}
