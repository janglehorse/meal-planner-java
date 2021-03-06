package com.calebtravers.models.data;

import com.calebtravers.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adminbackup on 5/4/17.
 */
@Repository
@Transactional
public interface RecipeDao extends CrudRepository<Recipe, Integer>{

    List<Recipe> findByName(String name);

}
