package com.rogeriogregorio.bookmanagementsystem.services.impl;

import com.rogeriogregorio.bookmanagementsystem.entities.UserEntity;
import com.rogeriogregorio.bookmanagementsystem.entities.UserRole;
import com.rogeriogregorio.bookmanagementsystem.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByLogin(username);
    }

    @PostConstruct
    public void createAdminUser() {
        if (userRepository.findByLogin("adminLogin") == null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode("adminPassword");
            UserEntity adminUser = new UserEntity("adminLogin", encryptedPassword, UserRole.ADMIN);
            userRepository.save(adminUser);
        }
    }
}
