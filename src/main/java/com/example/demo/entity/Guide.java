package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@Entity
public class Guide {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", nullable = false)
    private Integer map_id;


    private  String name;

    private Integer city_id;

    private String city_name;

    private  Integer line_id;

    private Integer beigin_id;

    @ManyToMany(mappedBy = "guideList")
    private List<Player>playerList;
}
