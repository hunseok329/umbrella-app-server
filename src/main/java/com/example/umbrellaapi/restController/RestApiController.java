package com.example.umbrellaapi.restController;

import com.example.umbrellaapi.restService.GetWeatherService;
import com.example.umbrellaapi.restService.LocationChangeService;
import com.example.umbrellaapi.restService.TimeControlService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class RestApiController {

    @Autowired
    private LocationChangeService locationChangeService;
    @Autowired
    private TimeControlService timeControlService;
    @Autowired
    private GetWeatherService getWeatherService;

    // 날씨 데이터 호출
    @GetMapping("/weather")
    public @ResponseBody JSONObject projectInfo(@RequestParam double lat, @RequestParam double lon) throws IOException, ParseException {
        // 위도, 경도값을 날씨API에서 사용하는 좌표값으로 변경
        int[] location = locationChangeService.mapToGrid(lat, lon);

        // 현재 시간값을 추출 dateTime[0] = base_date ( ex: 20230110 ), dateTime[1] = base_time ( ex: 20:20 )
        String[] dateTime = timeControlService.getNowDate();

        // 날씨 API에 필요한 target_time 추출
        String target_time = timeControlService.getTargetTime(dateTime[1]);

        // 단기예보조회 API를 요청하여 날씨 데이터를 받아옵니다.
        JSONObject weather_data = getWeatherService.getWeatherData(location[0], location[1], dateTime[0], target_time);

        return weather_data;
    }
}
