package com.codecool.charity.senders;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ConfigurationProperties
@EnableConfigurationProperties
public class SenderServiceImpl implements SenderService {

    private SenderRepository repository;

    public SenderServiceImpl(SenderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Sender> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Sender findOne(Long id) {

        Optional<Sender> sender = repository.findById(id);

        return sender.get();
    }

    @Override
    public void save(Sender sender) {

        this.repository.save(sender);
    }

}
