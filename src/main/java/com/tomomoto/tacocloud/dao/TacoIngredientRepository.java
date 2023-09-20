package com.tomomoto.tacocloud.dao;

import com.tomomoto.tacocloud.taco.TacoIngredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoIngredientRepository extends CrudRepository<TacoIngredient, Integer> {
}
