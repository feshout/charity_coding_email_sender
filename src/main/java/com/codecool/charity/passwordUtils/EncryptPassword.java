package com.codecool.charity.passwordUtils;

import org.springframework.beans.factory.annotation.Value;

public class EncryptPassword {

    @Value("${key.for.cipher}")
    private String keyForCipher;

    public String getSecuredPassword(String myPass) {
        return PasswordUtils.generateSecurePassword(myPass, keyForCipher);
    }

}
