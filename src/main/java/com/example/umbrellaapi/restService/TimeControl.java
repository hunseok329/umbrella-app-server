package com.example.umbrellaapi.restService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeControl implements TimeControlService{

    // 현재 시간 추출
    public String[] getNowDate() {
        LocalDateTime date = LocalDateTime.now();


        String base_date = date.format(DateTimeFormatter.ofPattern("yyyyMMdd")); // 현재 날짜
        String base_time = date.format(DateTimeFormatter.ofPattern("HH:mm")); // 현재 시간

        String[] base_date_time = new String[] { base_date, base_time};

        return base_date_time;
    }

    // API에서 사용할 시간대 선택
    public String getTargetTime(String base_time){
        String[] hour_minute = base_time.split(":");

        String hour = hour_minute[0];
        String min = hour_minute[1];
        int real_time = Integer.valueOf(hour + min);

        int[] target_base_time = {200, 500, 800, 1100, 1400, 1700, 2000, 2300};

        int target_time = 200;

        for (int time:target_base_time) {
            int time10 = time + 10;

            if( real_time >= time10) {
                target_time = time10;
            }
        }

        if (target_time < 1000) {
            return "0" + String.valueOf(target_time);
        }
        return String.valueOf(target_time);
    }
}
