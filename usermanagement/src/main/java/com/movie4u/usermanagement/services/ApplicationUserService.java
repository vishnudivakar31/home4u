package com.movie4u.usermanagement.services;

import com.movie4u.usermanagement.models.ApplicationUser;
import com.movie4u.usermanagement.repositories.ApplicationUserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ApplicationUserService implements UserDetailsService {
    private ApplicationUserRepo applicationUserRepo;

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
}
