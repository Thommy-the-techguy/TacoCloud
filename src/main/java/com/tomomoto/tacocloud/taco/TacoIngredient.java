package com.tomomoto.tacocloud.taco;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "taco_ingredient")
public class TacoIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private IngredientType type;
    @ManyToOne(optional = false)
    @JoinColumn(name = "taco_id")
    private Taco taco;
}
