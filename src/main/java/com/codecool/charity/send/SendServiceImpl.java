package com.codecool.charity.send;

import org.springframework.stereotype.Service;

@Service
public class SendServiceImpl implements SendService {

    private SendRepository repository;

    public SendServiceImpl(SendRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Send send) {
        this.repository.save(send);
    }

    @Override
    public void sandEmail(Send send) {

    }
}
