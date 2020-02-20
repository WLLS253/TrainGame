package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Player {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint", nullable = false)
    private Integer user_id;

    private  Integer high_score;

    private  Integer grade;

    private  Integer coin;



    @ManyToMany
    @JoinTable(
            name = "player_train",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id",referencedColumnName = "item_id")
    )
    private List<Train>trainList;

    @ManyToMany
    @JoinTable(
            name = "player_Guide",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "map_id",referencedColumnName = "map_id")
    )
    private List<Guide>guideList;
}
