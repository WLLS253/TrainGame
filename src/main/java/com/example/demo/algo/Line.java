package com.example.demo.algo;

import com.example.demo.entity.MetroLine;
import com.google.gson.Gson;

import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Line {
    private static Gson gson = new Gson();

    private String lineId;
    private String lineName;
    private List<Station> station;

    public enum Shape {
        line, circle
    }

    private Shape shape;

    // angle degrees between the line and the x axis
    private int initialDirection;

    public void bindMetroLineData(MetroLine metroLine) {
        shape = Shape.valueOf(metroLine.getShape());
        initialDirection = metroLine.getInitialDirection();
        int[][] turnings = gson.fromJson(metroLine.getTurnings(), int[][].class);
        for (int[] turning : turnings) {
            int idx = turning[0];
            int degree = turning[1];
            station.get(idx).setTurning(degree);
        }
    }
}
