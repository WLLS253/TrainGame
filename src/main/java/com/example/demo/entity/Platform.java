package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", nullable = false)
    private Long id;

    private Integer level;

    private Integer max_capacity;

    private Integer min_capacity;

    private Integer max_grow_speed;

    private  Integer min_grow_speed;

    private  Integer gen_interval;

    private  Integer grow_interval;


}
