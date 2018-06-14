package com.codecool.charity.passwordUtils;

import org.springframework.stereotype.Service;

@Service
public class VerifyUserPassword {

    public boolean verifyUserPassword(String userPass, String securedPass, String salt) {
        return PasswordUtils.verifyUserPassword(userPass, securedPass, salt);
    }
}
