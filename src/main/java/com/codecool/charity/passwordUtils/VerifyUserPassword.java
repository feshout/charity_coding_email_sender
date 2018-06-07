package com.codecool.charity.passwordUtils;

import org.springframework.beans.factory.annotation.Value;

public class VerifyUserPassword {

    @Value("${key.for.cipher}")
    private String keyForCipher;

    public boolean verifyUserPassword(String userPass) {
        boolean result = false;
//        String securedPass = z bazy po id usera zalogowanego do App
//        result = PasswordUtils.verifyUserPassword(userPass, securedPass, keyForCipher);

        return result;
    }
}
