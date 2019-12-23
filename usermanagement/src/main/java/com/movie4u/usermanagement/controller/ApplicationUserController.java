package com.movie4u.usermanagement.controller;

import com.movie4u.usermanagement.models.ApplicationUser;
import com.movie4u.usermanagement.repositories.ApplicationUserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class ApplicationUserController {

    private ApplicationUserRepo applicationUserRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserController(ApplicationUserRepo applicationUserRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepo = applicationUserRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        applicationUserRepo.save(appUser);
    }
}
