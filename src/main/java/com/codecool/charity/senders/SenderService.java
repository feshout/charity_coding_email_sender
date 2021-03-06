package com.codecool.charity.senders;

import java.util.Optional;

public interface SenderService {

    public Iterable<Sender> findAll();
    void save(Sender sender);
    public Sender findOne(Long id);
}
