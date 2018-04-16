package com.codecool.charity.senders;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
    public Optional<Sender> findOne(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void save(Sender sender) {
        if (this.repository.findSenderByHostName(sender.getHostName()) == null) {
            this.repository.save(sender);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
