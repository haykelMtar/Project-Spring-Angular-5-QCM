package org.exam;


import org.exam.dao.RoleRepository;
import org.exam.dao.UserRepository;
import org.exam.entities.Role;
import org.exam.entities.User;
import org.exam.services.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class GExamApplication {
	
	

	@Bean 
	public BCryptPasswordEncoder BCEP() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
	
		ApplicationContext ctx = SpringApplication.run(GExamApplication.class, args);
		
		UserRestService us =ctx.getBean(UserRestService.class);
		RoleRepository ro =ctx.getBean(RoleRepository.class);
		
		Role r1 = new Role("ADMIN", "ADMIN");
		Role r2 = new Role("USER", "USER");
		
		ArrayList<Role> roleAdmin = new ArrayList<>();
		roleAdmin.add(r1);
		roleAdmin.add(r2);
		
		ArrayList<Role> roleUser = new ArrayList<>();
		roleUser.add(r2);
		
		
		
		User u1 = new User();
		u1.setUsername("admin");
		u1.setPassword("1234");
		u1.setRoles(roleAdmin);
		User u2 = new User();
		u2.setUsername("user");
		u2.setPassword("1234");
		u2.setRoles(roleUser);

		ro.save(r1);
		ro.save(r2);
		
		us.saveUser(u1);
		us.saveUser(u2);

		
	}
	
	
	

	
}
