package com.calebtravers.models.data;

import com.calebtravers.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by adminbackup on 5/26/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
