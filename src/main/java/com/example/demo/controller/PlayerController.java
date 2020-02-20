package com.example.demo.controller;


import com.example.demo.Enums.ExceptionEnums;
import com.example.demo.Result.Result;
import com.example.demo.Serivce.PlayerService;
import com.example.demo.Util.Util;
import com.example.demo.entity.Player;
import com.example.demo.entity.Train;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private PlayerService playerService;


    @PostMapping(value = "/player/login")
    public Result dologin(@RequestParam("user_id")Integer user_id){
        try {
            Player player=playerRepository.findById(user_id).get();
            return Util.success(player);
        } catch (NoSuchElementException e){
            Player player1=new Player();
            player1.setUser_id(user_id);
            return Util.success(playerRepository.save(player1));
        } catch (Exception e){
            e.printStackTrace();
            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }
    }

    @PostMapping(value = "/player/addTrain")
    public Result addTrain(@RequestParam("user_id")Integer user_id,@RequestParam("train_id")Integer train_id){
        try{
            Player player=playerRepository.findById(user_id).get();
            Train train=trainRepository.findById(train_id).get();
            List<Train>trainList=player.getTrainList();
            trainList.add(train);
            player.setTrainList(trainList);
            List<Player>playerList=train.getPlayerList();
            playerList.add(player);
            train.setPlayerList(playerList);
            trainRepository.save(train);
            playerRepository.save(player);
            return Util.success(playerService.getPlayerJson(player));
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return Util.failure(ExceptionEnums.UNFIND_DATA_ERROR);
        } catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }
    }

    @GetMapping(value = "/player/getplayer/{user_id}")
    public  Result getplayer(@PathVariable("user_id")Integer user_id){
        try {
            Player player=playerRepository.findById(user_id).get();
                return Util.success(playerService.getPlayerJson(player));
        }catch (NoSuchElementException e){
            Player player1=new Player();
            player1.setUser_id(user_id);
            return Util.success(playerRepository.save(player1));
        } catch (Exception e){
            e.printStackTrace();
            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }
    }


    @PutMapping(value = "/player/updatePlayer")
    public  Result updatePlayer(@RequestParam("user_id")Integer user_id,@RequestParam("high_score")Integer high_score,@RequestParam("coin")Integer coin){
        try {
            return Util.success();
        }catch (Exception e){
            e.printStackTrace();
            return Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }
    }


    @GetMapping(value = "/player/high_score/{number}")
    public  Result getHigh_scorePlayer(@PathVariable("number")Integer number){
        try {
            List<Player>playerList=playerService.getHigh_score().subList(0,number-1);
            return  Util.success(playerService.getHigh_scoreJson(playerList));
        }catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }
    }

    @DeleteMapping(value = "/player/del/train/{user_id}/{train_id}")
    public  Result delPlaterTrain(@PathVariable("user_id")Integer user_id,@PathVariable("train_id")Integer train_id){
        try {
            Player player=playerRepository.findById(user_id).get();
            Train train=trainRepository.findById(train_id).get();

            List<Train>trainList=player.getTrainList();
            trainList.remove(train);
            player.setTrainList(trainList);
            List<Player>playerList=train.getPlayerList();
            playerList.remove(player);
            train.setPlayerList(playerList);
            trainRepository.save(train);
            playerRepository.save(player);
            return Util.success(playerService.getPlayerJson(player));
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return Util.failure(ExceptionEnums.UNFIND_DATA_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return  Util.failure(ExceptionEnums.UNKNOW_ERRPR);
        }
    }



}
