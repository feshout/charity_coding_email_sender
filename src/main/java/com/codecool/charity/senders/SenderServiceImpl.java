package com.codecool.charity.senders;

import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

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

        Properties p = new Properties();
        Optional<Sender> sender = repository.findById(id);

        try {

            FileReader f = new FileReader("src/main/resources/application.properties");

            p.load(f);
            if (sender.isPresent()){
                p.setProperty("spring.mail.username", sender.get().getHostName());
                p.setProperty("spring.mail.password", sender.get().getPassword());
            }
            p.store(new FileWriter("src/main/resources/application.properties"), null);
            System.out.println(p.getProperty("spring.mail.username"));
            System.out.println(p.getProperty("spring.mail.password"));
        } catch (IOException e){
            e.printStackTrace();
        }

        return sender;
    }

    @Override
    public void save(Sender sender) {

        System.out.println(sender.getHostName());


        if (this.repository.findSenderByHostName(sender.getHostName()) == null) {
            this.repository.save(sender);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
