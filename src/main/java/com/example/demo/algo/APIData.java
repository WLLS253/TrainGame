package com.example.demo.algo;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.*;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class APIData {
    private static Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

    public static List<Line> getMetroByCityCode(int cityCode) {
        String url = "http://59.110.174.204:7280/v1.0/api/metro/station/find";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        RequestBody requestBody = new FormBody.Builder()
                .add("city_id", "" + cityCode)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call syncCall = okHttpClient.newCall(request);
        try {
            Response response = syncCall.execute();
            String strData = response.body().string();
            RespData respData = gson.fromJson(strData, RespData.class);
            return respData.getResult().getPage().getLineSta();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class RespData {
        private Result result;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Result {
        private Page page;
    }
}
