package com.jwtproject.userSecurity.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwtproject.userSecurity.Entity.Role;
import com.jwtproject.userSecurity.Entity.User;
import com.jwtproject.userSecurity.Form.SignupForm;
import com.jwtproject.userSecurity.Response.MessageResponse;
import com.jwtproject.userSecurity.Service.RoleService;
import com.jwtproject.userSecurity.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) //Token Expired 10 Minute
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                log.error("Error logging in : {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupForm signupForm){
        try {
            if(userService.existsByUsername(signupForm.getUsername())){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse(false,"Failed","Error: Username is already taken!"));
            }else{
                //Create User
                User user = new User(null, signupForm.getName(), signupForm.getUsername(), signupForm.getPassword(), new ArrayList<>());

                Set<String> rolesRequest = signupForm.getRoles();
                Collection<Role> roles = new ArrayList<>();

                rolesRequest.forEach(role -> {
                    switch (role){
                        case "admin" :
                            Role adminRole = roleService.findByName("ROLE_ADMIN");
                            roles.add(adminRole);
                            break;
                        case "manager" :
                            Role managerRole = roleService.findByName("ROLE_MANAGER");
                            roles.add(managerRole);
                            break;
                        case "user" :
                            Role userRole = roleService.findByName("ROLE_USER");
                            roles.add(userRole);
                            break;
                        case "superadmin" :
                            Role superAdminRole = roleService.findByName("ROLE_SUPER_ADMIN");
                            roles.add(superAdminRole);
                            break;
                        default:
                            Role userDefaultRole = roleService.findByName("ROLE_USER");
                            roles.add(userDefaultRole);
                    }
                });

                user.setRoles(roles);
                userService.saveUser(user);
                return ResponseEntity.ok(new MessageResponse(true,"Succés","User registered successfully!"));
            }

        }catch(Exception exception){
            return ResponseEntity.ok(new MessageResponse(false,"failed",exception.getMessage()));
        }
    }
}
