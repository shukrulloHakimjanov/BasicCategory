package com.example.Category.model.user.auth;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
//import javax.validation.constraints.Email;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter

public class RegisterRequest {
    private String firstname;
    private String lasttname; //TODO typo
    @NotEmpty(message = "{email.notempty}")
    @Email
    private String email;
    @Size(min = 6)
    private String password;
}
