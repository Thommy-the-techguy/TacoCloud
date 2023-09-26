package com.tomomoto.tacocloud.controller;

import com.sun.security.auth.UserPrincipal;
import com.tomomoto.tacocloud.dao.TacoIngredientRepository;
import com.tomomoto.tacocloud.dao.TacoOrderRepository;
import com.tomomoto.tacocloud.dao.TacoRepository;
import com.tomomoto.tacocloud.entity.User;
import com.tomomoto.tacocloud.taco.TacoIngredient;
import com.tomomoto.tacocloud.taco.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private TacoOrderRepository tacoOrderRepository;
    private TacoIngredientRepository tacoIngredientRepository;
    private TacoRepository tacoRepository;

    @Autowired
    public OrderController(TacoOrderRepository tacoOrderRepository, TacoIngredientRepository tacoIngredientRepository, TacoRepository tacoRepository) {
        this.tacoOrderRepository = tacoOrderRepository;
        this.tacoIngredientRepository = tacoIngredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/current")
    public String currentOrder() {
        return "currentOrder";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "currentOrder";
        }
        tacoOrder.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        tacoOrderRepository.save(tacoOrder);
        tacoOrder.getTacos().forEach(taco -> taco.setTacoOrder(tacoOrder));
        tacoOrder.getTacos().forEach(taco -> tacoRepository.save(taco));
        tacoOrder.getTacos().forEach(taco -> taco.getIngredients().forEach(tacoIngredient -> tacoIngredientRepository.save(tacoIngredient)));

        String receipt = """
                    ---------  Paycheck  ---------
                    Name: %s
                    Street address: %s
                    City: %s
                    State: %s
                    Zip code: %s
                    --------- Credentials ---------
                    Credit card #: %s
                    Expiration date: %s
                    CVV: %s
                """.formatted(tacoOrder.getDeliveryName(),
                tacoOrder.getDeliveryStreet(),
                tacoOrder.getDeliveryCity(),
                tacoOrder.getDeliveryState(),
                tacoOrder.getDeliveryZip(),
                tacoOrder.getCcNumber(),
                tacoOrder.getCcExpiration(),
                tacoOrder.getCcCVV());
        log.info("Order submitted: {}", receipt);
        Path receiptPath = Path.of("src", "main", "resources", "static", "receipt.txt");
        try {
            Files.writeString(receiptPath, receipt, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sessionStatus.setComplete();
        return "receipt";
    }
}
