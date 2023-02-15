package com.test.salon.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientName;
    private String number;
    private String mail;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private User client;

    public Contact(String clientName, String number, String mail, User client) {
        this.clientName = clientName;
        this.number = number;
        this.mail = mail;
        this.client = client;
    }
}
