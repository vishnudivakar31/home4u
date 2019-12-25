package com.home4u.usermanagement.controller;

import com.home4u.usermanagement.models.ApplicationUser;
import com.home4u.usermanagement.repositories.ApplicationUserRepo;
import com.home4u.usermanagement.services.ApplicationUserService;
import com.home4u.usermanagement.util.UserManagementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    private ApplicationUserRepo applicationUserRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ApplicationUserService applicationUserService;
    private Logger logger = LoggerFactory.getLogger(ApplicationUser.class);
    private final String MESSAGE = "MESSAGE";
    private final String USER_REGISTRATION_SUCCESSFUL = "user registration successful";
    private final String USER_ALREADY_EXISTS = "user already exists";
    private final String USER_NOT_FOUND = "user not found";

    public ApplicationUserController(ApplicationUserRepo applicationUserRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
                                     ApplicationUserService applicationUserService) {
        this.applicationUserRepo = applicationUserRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody ApplicationUser appUser) {
        Map<String, String> response = new HashMap<String, String>();
        HttpStatus status;
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        try {
            appUser.setActive(true);
            appUser.setBlocked(false);
            appUser.setJoindate(new Date(System.currentTimeMillis()));
            appUser.setUpdateddate(new Date(System.currentTimeMillis()));
            applicationUserRepo.save(appUser);
            response.put(MESSAGE, USER_REGISTRATION_SUCCESSFUL);
            status = HttpStatus.OK;
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.put(MESSAGE, USER_ALREADY_EXISTS);
            status = HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<>(response, status);
    }

    @GetMapping
    public ResponseEntity<Object> getAppUser(Principal principal) {
        HttpStatus status;
        ResponseEntity responseEntity;
        try {
            ApplicationUser user = applicationUserService.loadUserByPrincipal(principal);
            status = HttpStatus.OK;
            responseEntity = new ResponseEntity(user, status);
        } catch (UsernameNotFoundException exception) {
            status = HttpStatus.NOT_FOUND;
            Map<String, String> response = new HashMap<>();
            response.put(MESSAGE, USER_NOT_FOUND);
            responseEntity = new ResponseEntity(response, status);
        }
        return responseEntity;
    }

    @PostMapping("/revive")
    public ResponseEntity<Object> reviveUser(Principal principal) {
        ResponseEntity responseEntity;
        try {
            ApplicationUser user = applicationUserService.reviveAppUser(principal);
            responseEntity = new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put(MESSAGE, USER_NOT_FOUND);
            responseEntity = new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PutMapping()
    public ResponseEntity<Object> updateAppUser(@RequestBody ApplicationUser updatedUser, Principal principal) {
        ResponseEntity responseEntity;
        try {
            ApplicationUser user = applicationUserService.updateUserByPrincipal(principal, updatedUser);
            responseEntity = new ResponseEntity(user, HttpStatus.OK);
        } catch (UserManagementException e) {
            logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put(MESSAGE, e.getMessage());
            responseEntity = new ResponseEntity(response, HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAppUser(Principal principal) {
        ResponseEntity responseEntity;
        try {
            ApplicationUser user = applicationUserService.deleteAppUser(principal);
            responseEntity = new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put(MESSAGE, USER_NOT_FOUND);
            responseEntity = new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
