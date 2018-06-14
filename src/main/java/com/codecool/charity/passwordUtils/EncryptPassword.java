package com.codecool.charity.passwordUtils;

import org.springframework.stereotype.Service;

@Service
public class EncryptPassword {

    public String getSecuredPassword(String myPass, String salt) {
        return PasswordUtils.generateSecurePassword(myPass, salt);
    }

}
