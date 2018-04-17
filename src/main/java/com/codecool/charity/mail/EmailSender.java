package com.codecool.charity.mail;

import com.codecool.charity.send.Send;

public interface EmailSender {

    void sendEmail(Send send);
}
