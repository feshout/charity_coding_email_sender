package com.codecool.charity.send;


import com.codecool.charity.senders.Sender;
import com.codecool.charity.templates.Template;

import javax.persistence.*;

@Entity
public class Send {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String receiver;

//    cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Sender sender;


//    cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    @OneToOne
    @JoinColumn(name = "template_id")
    private Template template;

    public Long getId() {
        return id;
    }

    public String getReceiver() {
        return receiver;
    }

    public Sender getSender() {
        return sender;
    }

    public Template getTemplate() {
        return template;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
