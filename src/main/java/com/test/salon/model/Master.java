package com.test.salon.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Integer rang;

    public Master() {
    }

    public Master(String name, String surname, Integer rang) {
        this.name = name;
        this.surname = surname;
        this.rang = rang;
    }

}
