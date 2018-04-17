package com.codecool.charity.send;

public interface SendService {

    void save(Send send);

    void sandEmail(Send send);
}
