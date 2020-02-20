package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.PrivateKey;


@Setter
@Getter
@Entity
public class MetroLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", nullable = false)
    private Long id;

    private Integer cityCode;

    private String lineId;

    private String shape;

    private Integer initialDirection;

    private String turnings;
}
