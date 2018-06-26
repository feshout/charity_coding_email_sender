package com.codecool.charity.senders;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SenderRepository extends CrudRepository<Sender, Long> {

    Iterable<Sender> findAll();

    Sender findById(Long id);

    Sender findSenderByHostName(String hostName);

    void delete(Sender sender);
}
