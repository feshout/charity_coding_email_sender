package com.codecool.charity.senders;

import com.codecool.charity.passwordUtils.EncryptPassword;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@ConfigurationProperties
@EnableConfigurationProperties
public class SenderServiceImpl implements SenderService {

    private SenderRepository repository;
    private EncryptPassword encryptPassword;

    public SenderServiceImpl(SenderRepository repository, EncryptPassword encryptPassword) {
        this.repository = repository;
        this.encryptPassword = encryptPassword;
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
        String securedPass = generateSecuredPassword(sender);
        sender.setPassword(securedPass);
        this.repository.save(sender);
    }

    private String generateSecuredPassword(Sender sender) {
        return this.encryptPassword.getSecuredPassword(sender.getPassword());
    }

}
