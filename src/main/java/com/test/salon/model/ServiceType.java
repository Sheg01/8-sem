package com.test.salon.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer price;
    private String serviceGroup;
    private String description;
    private Integer rang;

    public ServiceType() {
    }

    public ServiceType(String name, Integer price, String serviceGroup, String description, Integer rang) {
        this.name = name;
        this.price = price;
        this.serviceGroup = serviceGroup;
        this.description = description;
        this.rang = rang;
    }
}
