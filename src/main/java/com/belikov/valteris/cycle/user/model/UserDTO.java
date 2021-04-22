package com.belikov.valteris.cycle.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username is required!")
    @Size(min = 3, max = 32, message = "Name must be between 3 and 32 characters long!")
    private String username;

    @Email(message = "Wrong email! Use only Aa-Zz, 0-9, ._@")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Passwords can't be empty!")
    @Size(min = 5, message = "Password must be more than 5 symbols!")
    private String password;

    @NotBlank(message = "Passwords can't be empty!")
    @Size(min = 5, message = "Password must be more than 5 symbols!")
    private String rePassword;

}
