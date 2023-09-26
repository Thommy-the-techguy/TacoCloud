package com.tomomoto.tacocloud.taco;

import com.tomomoto.tacocloud.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "taco_order")
public class TacoOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "delivery_name")
    @NotBlank(message = "Must be filled")
    private String deliveryName;
    @Column(name = "delivery_street")
    @NotBlank(message = "Must be filled")
    private String deliveryStreet;
    @Column(name = "delivery_city")
    @NotBlank(message = "Must be filled")
    private String deliveryCity;
    @Column(name = "delivery_state")
    @NotBlank(message = "Must be filled")
    private String deliveryState;
    @Column(name = "delivery_zip")
    @NotBlank(message = "Must be filled")
    private String deliveryZip;
    @Column(name = "cc_number")
    @CreditCardNumber(message = "Invalid credit card number")
    private String ccNumber;
    @Column(name = "cc_expiration")
    @Pattern(regexp = "(0[1-9]|1[0-2])/([2-9][3-9])", message = "Invalid expiration date")
    private String ccExpiration;
    @Column(name = "cc_CVV")
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    @OneToMany(mappedBy = "tacoOrder")
    private List<Taco> tacos = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
