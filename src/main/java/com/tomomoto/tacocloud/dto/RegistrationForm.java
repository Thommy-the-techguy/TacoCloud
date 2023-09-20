package com.tomomoto.tacocloud.dto;

import com.tomomoto.tacocloud.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Data
@Component
public class RegistrationForm {
    private PasswordEncoder passwordEncoder;

    public RegistrationForm(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @NotBlank(message = "username shouldn't be blank")
    @Size(min = 4, max = 15, message = "username size should be more than 4 symbols and less than 15 symbols")
    private String username;
    @NotBlank(message = "password shouldn't be blank")
    @Size(min = 5, max = 15, message = "password should be more than 5 symbols and lass than 15")
    private String password;
    @NotBlank(message = "email shouldn't be blank")
    @Email(message = "Invalid email")
    @Pattern(regexp = "(.+)@([a-zA-Z]+)\\.([a-zA-Z]{2,})", message = "Invalid email format")
    private String email;

    public User toUser() {
        return User.builder()
                .username(this.username)
                .password(passwordEncoder.encode(this.password))
                .email(this.email)
                .build();
    }
}
