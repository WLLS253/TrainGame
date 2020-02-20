package com.example.demo.algo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Station {
    private final String stationId;
    private final String stationName;
    private final String stationNo;

    // the turning angle to next station
    // a positive angle means turning to the left side of the drawing direction
    private int turning;
}
