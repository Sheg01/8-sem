package com.test.salon.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name="ordr")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User client;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="service_type_id")
    private ServiceType serviceTypeName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="master_id")
    private Master master;

    private String orderDate;
    private String orderTime;
    private String orderStatus;

    public Order() {
    }

    public Order(User client, ServiceType serviceTypeName, Master master,
                 String orderDate, String orderTime, String orderStatus) {
        this.client = client;
        this.serviceTypeName = serviceTypeName;
        this.master = master;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }
}
