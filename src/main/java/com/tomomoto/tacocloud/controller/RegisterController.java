package com.tomomoto.tacocloud.controller;

import com.tomomoto.tacocloud.dao.UserRepository;
import com.tomomoto.tacocloud.dto.RegistrationForm;
import com.tomomoto.tacocloud.entity.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/register")
@SessionAttributes("user")
public class RegisterController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute(name = "registrationForm")
    public RegistrationForm createRegistrationForm() {
        return new RegistrationForm(passwordEncoder);
    }

    @ModelAttribute(name = "user")
    public User getUser() {
        return new User();
    }

    @GetMapping
    public String registrationForm() {
        return "register";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm registrationForm, Errors errors, @ModelAttribute User user) {
        if (errors.hasErrors()) {
            return "register";
        }
        log.info("Processing user {}", registrationForm.toUser());
        User formUser = registrationForm.toUser();
        User actualUser = userRepository.save(formUser);
        user.setId(actualUser.getId());
        user.setUsername(actualUser.getUsername());
        user.setPassword(actualUser.getPassword());
        user.setEmail(actualUser.getEmail());
        return "redirect:/login";
    }
}
