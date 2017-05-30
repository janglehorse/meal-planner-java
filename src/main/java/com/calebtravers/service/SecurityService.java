package com.calebtravers.service;

/**
 * Created by adminbackup on 5/30/17.
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autologin(String username, String password);
}
