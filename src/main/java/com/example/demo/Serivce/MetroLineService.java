package com.example.demo.Serivce;


import com.example.demo.algo.CityNotFoundException;
import com.example.demo.controller.MetroGenerateController;
import com.example.demo.entity.MetroLine;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Scanner;

@Service
public class MetroLineService {


    public  boolean debug = true;

    private static Gson gson = new Gson();

    @Setter
    @Getter
    @AllArgsConstructor
    private static class MetroLinePage {
        private List<MetroLine> metroLines;
    }

    public static List<MetroLine> getMetroLines(int cityCode) throws CityNotFoundException {
        ClassLoader classLoader = MetroGenerateController.class.getClassLoader();
        String filename = null;
        switch (cityCode) {
            case 3202:
                filename = classLoader.getResource("data/wuxi.json").getFile();
                break;
            case 1001:
                filename = classLoader.getResource("data/beijing.json").getFile();
                break;
            default:
                throw new CityNotFoundException(cityCode);
        }
        try {
            if (filename != null) {
                filename = URLDecoder.decode(filename, "utf-8");
                Scanner scanner = new Scanner(new FileInputStream(filename));
                StringBuilder data = new StringBuilder();
                while (scanner.hasNextLine()) {
                    data.append(scanner.nextLine());
                }
                System.out.println(data);
                MetroLinePage page = gson.fromJson(data.toString(), MetroLinePage.class);
                return page.getMetroLines();
            } else {
                throw new CityNotFoundException(cityCode);
            }
        } catch (IOException e) {
            throw new CityNotFoundException(cityCode);
        }
    }

//    @Setter
//    @Getter
//    @AllArgsConstructor
//    private static class MetroLinePage {
//        private List<MetroLine> metroLines;
//    }
//
//    public static List<MetroLine> getMetroLines(int cityCode) throws CityNotFoundException {
//        ClassLoader classLoader = MetroGenerateController.class.getClassLoader();
//        String filename = null;
//        switch (cityCode) {
//            case 3202:
//                filename = classLoader.getResource("data/wuxi.json").getFile();
//                break;
//            case 1001:
//                filename = classLoader.getResource("data/beijing.json").getFile();
//                break;
//            default:
//                throw new CityNotFoundException(cityCode);
//        }
//        try {
//            if (filename != null) {
//                filename = URLDecoder.decode(filename, "utf-8");
//                Scanner scanner = new Scanner(new FileInputStream(filename));
//                StringBuilder data = new StringBuilder();
//                while (scanner.hasNextLine()) {
//                    data.append(scanner.nextLine());
//                }
//
//                System.out.println(data);
//                MetroLinePage page = gson.fromJson(data.toString(), MetroLinePage.class);
//                return page.getMetroLines();
//            } else {
//                throw new CityNotFoundException(cityCode);
//            }
//        } catch (IOException e) {
//            throw new CityNotFoundException(cityCode);
//        }
//    }
}
