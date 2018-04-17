package com.codecool.charity.senders;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"hostName", "password", "id"}))
public class Sender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hostName;
    private String password;

    public Long getId() {
        return id;
    }

    public String getHostName() {
        return hostName;
    }

    public String getPassword() {
        return password;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
