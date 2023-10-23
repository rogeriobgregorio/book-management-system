package com.rogeriogregorio.bookmanagementsystem.controllers;

import com.rogeriogregorio.bookmanagementsystem.dto.LoginResponseDTO;
import com.rogeriogregorio.bookmanagementsystem.dto.UserLoginDTO;
import com.rogeriogregorio.bookmanagementsystem.dto.UserRegisterDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.UserEntity;
import com.rogeriogregorio.bookmanagementsystem.repositories.UserRepository;
import com.rogeriogregorio.bookmanagementsystem.services.impl.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(
                userLoginDTO.getLogin(), userLoginDTO.getPassword()
        );

        var authenticate = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) authenticate.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDTO userRegisterDTO) {

        if(this.userRepository.findByLogin(userRegisterDTO.getLogin()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterDTO.getPassword());
        var userEntity = new UserEntity(userRegisterDTO.getLogin(), encryptedPassword, userRegisterDTO.getRole());

        this.userRepository.save(userEntity);

        return ResponseEntity.ok().build();
    }
}
