package com.tomomoto.tacocloud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginForm {
    @NotBlank(message = "username shouldn't be empty")
    private String username;
    @NotBlank(message = "password shouldn't be empty")
    private String password;
}
