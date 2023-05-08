package com.jwtproject.userSecurity.Config;

import com.jwtproject.userSecurity.Entity.Role;
import com.jwtproject.userSecurity.Entity.User;
import com.jwtproject.userSecurity.Service.RoleService;
import com.jwtproject.userSecurity.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class UserConfig {
   // @Bean //{ Ajouter en cas de test }
    CommandLineRunner run(RoleService roleService, UserService userService){
        return args -> {
            roleService.saveRole(new Role(null, "ROLE_USER"));
            roleService.saveRole(new Role(null, "ROLE_ADMIN"));
            roleService.saveRole(new Role(null, "ROLE_MANAGER"));
            roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Amine guesmi", "Gassouma", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "ahmed", "ahmed1998", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Wiem Lagha", "wiwi", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "nihed", "nihed", "1234", new ArrayList<>()));

            userService.addRoleToUser("Gassouma", "ROLE_ADMIN");
            userService.addRoleToUser("Gassouma", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("Gassouma", "ROLE_MANAGER");
            userService.addRoleToUser("ahmed1998", "ROLE_USER");
            userService.addRoleToUser("wiwi", "ROLE_MANAGER");
            userService.addRoleToUser("nihed", "ROLE_SUPER_ADMIN");
        };
    }
}
