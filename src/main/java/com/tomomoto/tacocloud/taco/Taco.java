package com.tomomoto.tacocloud.taco;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "taco")
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Must not be empty")
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;
    @ManyToOne(optional = false)
    @JoinColumn(name = "taco_order_id")
    private TacoOrder tacoOrder;
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @NotNull
    @OneToMany(mappedBy = "taco")
    private List<TacoIngredient> ingredients;
}
