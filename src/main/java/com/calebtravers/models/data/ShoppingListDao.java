package com.calebtravers.models.data;
import com.calebtravers.models.Recipe;
import com.calebtravers.models.ShoppingList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by adminbackup on 5/19/17.
 */
public interface ShoppingListDao extends CrudRepository<ShoppingList, Integer> {

}
