package com.codecool.charity.mail;

public interface EmailSender {

    void sendEmail(String to, String subject, String content);
}
