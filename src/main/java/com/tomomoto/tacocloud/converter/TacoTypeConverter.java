package com.tomomoto.tacocloud.converter;

import com.tomomoto.tacocloud.dao.IngredientRepository;
import com.tomomoto.tacocloud.taco.Ingredient;
import com.tomomoto.tacocloud.taco.TacoIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TacoTypeConverter implements Converter<String, TacoIngredient> {
    private IngredientRepository ingredientRepository;

    @Autowired
    public TacoTypeConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public TacoIngredient convert(String id) {
        Ingredient ingredient = ingredientRepository.findById(Integer.parseInt(id)).get();
        return new TacoIngredient(ingredient.getId(), ingredient.getName(), ingredient.getType(), null);
    }
}
