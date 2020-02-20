package com.example.demo.Serivce;


import com.example.demo.entity.Guide;
import com.example.demo.entity.Player;
import com.example.demo.entity.Train;
import com.example.demo.repository.PlayerRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {


    @Autowired
    private PlayerRepository playerRepository;


    public JSONObject getPlayerJson(Player player) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id",player.getUser_id());
        jsonObject.put("high_score",player.getHigh_score());
        jsonObject.put("coin",player.getCoin());
        jsonObject.put("grade",player.getGrade());

        List<Train>trainList=player.getTrainList();
        JSONArray trains=new JSONArray();
        for(int i=0;i<trainList.size();i++){
            trains.add(i,trainList.get(i).getItem_id());
        }
        jsonObject.put("trains",trains);

        List<Guide>guideList=player.getGuideList();
        JSONArray guides=new JSONArray();
        for(int i=0;i<guideList.size();i++){
            guides.add(i,guideList.get(i).getMap_id());
        }
        jsonObject.put("guides",guides);

        return  jsonObject;
    }


    public  JSONObject getHigh_scoreJson(List<Player>playerList){


        JSONObject object=new JSONObject();
        JSONObject jsonObject=new JSONObject();
        JSONArray players=new JSONArray();
        for(int i=0;i<playerList.size();i++){
            jsonObject.put("user_id",playerList.get(i).getUser_id());
            jsonObject.put("high_score",playerList.get(i).getHigh_score());
            jsonObject.put("coin",playerList.get(i).getCoin());
            players.add(i,jsonObject);
        }
        object.element("PlayerList",players);

        return  object;
    }


    public  List<Player>getHigh_score(){
       List<Sort.Order>list=new ArrayList<>();
       Order order1=new Order(Sort.Direction.DESC,"high_score");
       list.add(order1);
       Sort sort=Sort.by(list);
       List<Player>playerList=playerRepository.findAll(sort);
       return playerList;

    }



}