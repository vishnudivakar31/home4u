package com.home4u.usermanagement.services;

import com.home4u.usermanagement.models.ApplicationUser;
import com.home4u.usermanagement.repositories.ApplicationUserRepo;
import com.home4u.usermanagement.util.UserManagementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;

@Service
public class ApplicationUserService implements UserDetailsService {
    private static final String USERNAME_TAKEN = "username already taken";
    private static final String EMAIL_TAKEN = "email already in use";
    private ApplicationUserRepo applicationUserRepo;
    private final String SECURED_PROPERTY = "secured property";

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserService(ApplicationUserRepo applicationUserRepo) {
        this.applicationUserRepo = applicationUserRepo;
    }

    public UserDetails loadUserByUsername(String username) {
        ApplicationUser applicationUser = applicationUserRepo.findByUsername(username);
        if(applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), Collections.emptyList());
    }

    public ApplicationUser loadUserByPrincipal(Principal principal) {
        ApplicationUser applicationUser = applicationUserRepo.findByUsername(principal.getName());
        if(applicationUser == null) {
            throw new UsernameNotFoundException(principal.getName());
        }
        applicationUser.setPassword(SECURED_PROPERTY);
        return applicationUser;
    }

    public ApplicationUser updateUserByPrincipal(Principal principal, ApplicationUser updatedUser) throws UserManagementException {
        ApplicationUser applicationUser = applicationUserRepo.findByUsername(principal.getName());
        if(applicationUser == null) {
            throw new UsernameNotFoundException(principal.getName());
        }
        if(updatedUser.getUsername() != null && !updatedUser.getUsername().equals(principal.getName())) {
            if(applicationUserRepo.findByUsername(updatedUser.getUsername()) != null) {
                throw new UserManagementException(USERNAME_TAKEN);
            }
            applicationUser.setUsername(updatedUser.getUsername());
        }
        if(updatedUser.getEmail() != null && !updatedUser.getEmail().equals(applicationUser.getEmail())) {
            if(applicationUserRepo.findByEmail(updatedUser.getEmail()) != null) {
                throw new UserManagementException(EMAIL_TAKEN);
            }
            applicationUser.setEmail(updatedUser.getEmail());
        }
        if(updatedUser.getDob() != null) {
            applicationUser.setDob(updatedUser.getDob());
        }
        if(updatedUser.getUsertype() != null) {
            applicationUser.setUsertype(updatedUser.getUsertype());
        }
        if(updatedUser.getPassword() != null) {
            applicationUser.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        }
        applicationUser.setUpdateddate(new Date(System.currentTimeMillis()));
        applicationUserRepo.save(applicationUser);
        return applicationUser;
    }

    public ApplicationUser deleteAppUser(Principal principal) {
        ApplicationUser applicationUser = applicationUserRepo.findByUsername(principal.getName());
        if(applicationUser == null) {
            throw new UsernameNotFoundException(principal.getName());
        }
        applicationUser.setActive(false);
        applicationUser.setUpdateddate(new Date(System.currentTimeMillis()));
        applicationUserRepo.save(applicationUser);
        return applicationUser;
    }

    public ApplicationUser reviveAppUser(Principal principal) {
        ApplicationUser applicationUser = applicationUserRepo.findByUsername(principal.getName());
        if(applicationUser == null) {
            throw new UsernameNotFoundException(principal.getName());
        }
        applicationUser.setActive(true);
        applicationUser.setUpdateddate(new Date(System.currentTimeMillis()));
        applicationUserRepo.save(applicationUser);
        return applicationUser;
    }
}
