package com.example.demo.controller;


import com.example.demo.Result.Result;
import com.example.demo.Serivce.MetroLineService;
import com.example.demo.Util.Util;
import com.example.demo.algo.CityNotFoundException;
import com.example.demo.algo.GenerateAlgorithm;
import com.example.demo.algo.Line;
import com.example.demo.algo.Page;
import com.example.demo.entity.MetroLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MetroGenerateController {

    @Autowired
    private MetroLineService metroLineService;

    @PostMapping(value = "/generateMetro")
    public Result generateMetro(@RequestParam("citycode")Integer cityCode) {

        List<MetroLine> metroLines = null;
        if (metroLineService.debug) {
            // 从JSON读取MetroLine (debug)
            try {
                metroLines = metroLineService.getMetroLines(cityCode);
            } catch (CityNotFoundException e) {
                e.printStackTrace();
                return Util.success( new Page());
            }
        } else {
            // 从数据库读取MetroLine (production)
            // TODO: 将liadrinz.persist.MetroLine加上持久化注解才能用
        }
        Page result;
        GenerateAlgorithm algorithm = new GenerateAlgorithm(cityCode, metroLines);
        List<Line> lines = algorithm.getLines();
        result = new Page(lines);
        return Util.success(result);
    }
}
