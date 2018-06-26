package com.codecool.charity.senders;

public interface SenderService {

    public Iterable<Sender> findAll();
    void save(Sender sender);
    public Sender findOne(Long id);

    void remove(Long id);
}
