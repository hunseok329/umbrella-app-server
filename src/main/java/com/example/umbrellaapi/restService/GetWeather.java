package com.example.umbrellaapi.restService;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class GetWeather implements GetWeatherService{

    // 공공 데이터 날씨 API URL
    private String target_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    // 날씨 API 인증키
    private String API_KEY = "siiOw9ZfDhBZ3QgLxYlOKTu4dloUBDKGSP6SkeU1nbZdq72vsB3SREoLX4xP4l%2FA9pUayhKSQsB0Ea9CAqCR%2BA%3D%3D";

    // 날씨 API 호출
    public JSONObject getWeatherData(int xlat, int ylon, String base_date, String base_time) throws IOException, ParseException {
        String numOfRows = "1000";
        String pageNo = "1";
        String dataType = "JSON";

        StringBuilder queryURL = new StringBuilder(target_URL);

        queryURL.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + API_KEY);
        queryURL.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" +URLEncoder.encode(pageNo, "UTF-8"));
        queryURL.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
        queryURL.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        queryURL.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8")); /*‘21년 6월 28일발표*/
        queryURL.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8")); /*05시 발표*/
        queryURL.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(String.valueOf(xlat), "UTF-8")); /*예보지점의 X 좌표값*/
        queryURL.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(String.valueOf(ylon), "UTF-8")); /*예보지점의 Y 좌표값*/

        URL url = new URL(queryURL.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        JSONObject jsonData = getJsonData(sb.toString());

        return jsonData;
    }

    // 날씨 API에서 받아온 데이터에서 필요한 정보 추출
    public JSONObject getJsonData(String test) throws ParseException {

        JSONParser jsonParser = new JSONParser();

        JSONObject obj = (JSONObject) jsonParser.parse(test);
        JSONObject response = (JSONObject)obj.get("response");
        JSONObject body = (JSONObject)response.get("body");
        JSONObject items = (JSONObject)body.get("items");

        return items;
    }

}
