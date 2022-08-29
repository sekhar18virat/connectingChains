package com.example.cc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Inventory")
public class Inventory {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "batch")
    private String batch;

    @Column(name = "stock")
    private int stock;

    @Column(name = "deal")
    private int deal;

    @Column(name = "free")
    private int free;

    @Column(name = "mrp")
    private double mrp;

    @Column(name = "rate")
    private double rate;

    @Column(name = "exp")
    private Date exp;

    @Column(name = "company")
    private String company;

    @Column(name = "supplier")
    private String supplier;


}
