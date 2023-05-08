package com.jwtproject.userSecurity.Form;

import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
public class SignupForm {
    private String name;
    private String username;
    private String password;
    private Set<String> roles;
}
