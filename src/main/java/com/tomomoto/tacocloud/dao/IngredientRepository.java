package com.tomomoto.tacocloud.dao;

import com.tomomoto.tacocloud.taco.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
