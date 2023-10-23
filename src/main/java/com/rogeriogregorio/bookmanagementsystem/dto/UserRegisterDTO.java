package com.rogeriogregorio.bookmanagementsystem.dto;

import com.rogeriogregorio.bookmanagementsystem.entities.UserRole;
import jakarta.validation.constraints.NotBlank;

public class UserRegisterDTO {

    @NotBlank(message = "O login não pode estar em branco")
    private String login;

    @NotBlank(message = "A senha não pode estar em branco")
    private String password;

    @NotBlank(message = "O tipo não pode estar em branco")
    private UserRole role;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
