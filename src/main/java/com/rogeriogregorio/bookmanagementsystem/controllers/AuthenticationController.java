package com.rogeriogregorio.bookmanagementsystem.controllers;

import com.rogeriogregorio.bookmanagementsystem.dto.LoginResponseDTO;
import com.rogeriogregorio.bookmanagementsystem.dto.UserLoginDTO;
import com.rogeriogregorio.bookmanagementsystem.dto.UserRegisterDTO;
import com.rogeriogregorio.bookmanagementsystem.entities.UserEntity;
import com.rogeriogregorio.bookmanagementsystem.repositories.UserRepository;
import com.rogeriogregorio.bookmanagementsystem.services.impl.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication API", description = "API para autenticação")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Operation(description = "Autenticar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "500", description = "Erro ao logar")
    })
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(
                userLoginDTO.getLogin(), userLoginDTO.getPassword()
        );

        var authenticate = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserEntity) authenticate.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @Operation(description = "Registrar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário já existente"),
            @ApiResponse(responseCode = "500", description = "Erro ao cadastrar-se")
    })
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
