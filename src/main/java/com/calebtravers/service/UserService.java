package com.calebtravers.service;

import com.calebtravers.models.User;

/**
 * Created by adminbackup on 5/30/17.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
