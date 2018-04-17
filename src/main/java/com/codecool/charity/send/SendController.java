package com.codecool.charity.send;

import com.codecool.charity.mail.EmailController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/send")
public class SendController {

    private EmailController emailController;

    @Autowired
    public SendController(EmailController emailController) { this.emailController = emailController;}

    @PostMapping(path = "")
    public Send sendEmail(@RequestBody Send send){
        this.emailController.send(send);
        return send;
    }
}
