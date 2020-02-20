package com.example.demo.algo;

public class CityNotFoundException extends Exception {
    private int wrongCityCode;

    public CityNotFoundException(int wrongCityCode) {
        this.wrongCityCode = wrongCityCode;
    }

    @Override
    public String getMessage() {
        return "City code " + wrongCityCode + " does not exist.";
    }
}
