package com.codecool.charity.senders;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/senders")
public class SenderController {

    private SenderService service;

    public SenderController(SenderService service) { this.service = service;}

    @GetMapping(path = "")
    public Iterable<Sender> index() {
        return this.service.findAll();
    }

    @GetMapping(path = "/{id}")
    public Optional<Sender> show(@PathVariable Long id) {
        return this.service.findOne(id);
    }

    @PostMapping(path = "")
    public Sender create(@RequestBody Sender sender) {
        this.service.save(sender);
        return sender;
    }
}
