package com.example.umbrellaapi.restService;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface GetWeatherService {
    public JSONObject getWeatherData(int xlat, int ylon, String base_date, String base_time ) throws IOException, ParseException;
}
