package com.codecool.charity.send;


import com.codecool.charity.senders.Sender;
import com.codecool.charity.templates.Template;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"id", "to"}))
public class Send {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String to;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Sender sender;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Template template;

    public Long getId() {
        return id;
    }

    public String getTo() {
        return to;
    }

    public Sender getSender() {
        return sender;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
