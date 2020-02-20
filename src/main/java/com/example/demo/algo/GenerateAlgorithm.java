package com.example.demo.algo;


import com.example.demo.entity.MetroLine;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateAlgorithm {

    @Getter
    @Setter
    private List<Line> lines;

    public GenerateAlgorithm(int cityCode, List<MetroLine> metroLines) {
        lines = APIData.getMetroByCityCode(cityCode);
        if (lines == null) {
            System.err.println("Could not fetch metro data from network");
            return;
        }
        Map<String, MetroLine> metroLineMap = new HashMap<String, MetroLine>();
        for (MetroLine metroLine : metroLines) {
            metroLineMap.put(metroLine.getLineId(), metroLine);
        }
        for (Line line : lines) {
            MetroLine metroLine = metroLineMap.get(line.getLineId());
            line.bindMetroLineData(metroLine);
        }
    }
}
