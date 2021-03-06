package com.calebtravers.models.data;

import com.calebtravers.models.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adminbackup on 5/9/17.
 */
@Repository
@Transactional
public interface IngredientDao extends CrudRepository<Ingredient, Integer> {

    List<Ingredient> findByRecipeId(int recipeId);

}
