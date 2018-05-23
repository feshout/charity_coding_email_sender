package com.codecool.charity.senders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

@Service
@ConfigurationProperties
@EnableConfigurationProperties
public class SenderServiceImpl implements SenderService {

    private SenderRepository repository;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    public SenderServiceImpl(SenderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Sender> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Sender> findOne(Long id) {

        Optional<Sender> sender = repository.findById(id);
        sender.ifPresent(this::refreshProperties);

        return sender;
    }

    @Override
    public void save(Sender sender) {

        if (this.repository.findSenderByHostName(sender.getHostName()) == null) {
            this.repository.save(sender);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void refreshProperties(Sender sender) {

        setUsername(sender.getHostName());
        setPassword(sender.getPassword());

//        Properties p = new Properties();
//
//        try {
//
//            FileReader f = new FileReader("src/main/resources/application.properties");
//
//            p.load(f);
//            p.setProperty("spring.mail.username", sender.getHostName());
//            p.setProperty("spring.mail.password", sender.getPassword());
//
//            p.store(new FileWriter("src/main/resources/application.properties"), null);
//            System.out.println(p.getProperty("spring.mail.username"));
//            System.out.println(p.getProperty("spring.mail.password"));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
