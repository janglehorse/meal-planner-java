package com.calebtravers.models.data;

import com.calebtravers.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by adminbackup on 5/26/17.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
