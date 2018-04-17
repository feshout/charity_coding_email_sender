package com.codecool.charity.send;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/send")
public class SendController {

    private SendService service;

    public SendController(SendService service) { this.service = service;}

    @PostMapping(path = "")
    public Send sendEmail(@RequestBody Send send){
        this.service.sandEmail(send);
        this.service.save(send);
        return send;
    }
}
