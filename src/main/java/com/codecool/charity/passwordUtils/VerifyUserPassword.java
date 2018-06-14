package com.codecool.charity.passwordUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VerifyUserPassword {

    @Value("${key.for.cipher}")
    private String keyForCipher;

    public boolean verifyUserPassword(String userPass, String securedPass) {
        return PasswordUtils.verifyUserPassword(userPass, securedPass, keyForCipher);
    }
}
