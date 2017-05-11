package com.calebtravers.models.data;

import com.calebtravers.models.Instruction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by adminbackup on 5/9/17.
 */
@Repository
@Transactional
public interface InstructionDao extends CrudRepository<Instruction, Integer> {

    List<Instruction> findByRecipeId(int recipeId);

}
