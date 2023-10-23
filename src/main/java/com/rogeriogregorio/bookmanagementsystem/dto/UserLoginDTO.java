package com.rogeriogregorio.bookmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {

    @NotBlank(message = "O login não pode estar em branco")
    private String login;

    @NotBlank(message = "A senha não pode estar em branco")
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
