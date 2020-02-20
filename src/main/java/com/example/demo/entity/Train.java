package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@Entity
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", nullable = false)
    private Integer item_id;

    private  String name;

    private Integer capacity;

    private  Integer speed;


    @ManyToMany(mappedBy = "trainList")
    private List<Player> playerList;
}
