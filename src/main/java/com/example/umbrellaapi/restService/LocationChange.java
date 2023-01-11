package com.example.umbrellaapi.restService;

import org.springframework.stereotype.Service;

@Service
public class LocationChange implements LocationChangeService{

    // 현재 위도와 경도 값을 좌표값으로 변경
    public int[] mapToGrid(double lat, double lon){
        int[] grid = new int[2];

        double RE = 6371.00977;
        double GRID = 5.0;
        double SLAT1 = 30.0;
        double SLAT2 = 60.0;
        double OLON = 126.0;
        double OLAT = 38.0;
        double XO = 43;
        double YO = 136;

        double DEGRAD = Math.PI / 180.0;
        double RADDEG = 180.0 / Math.PI;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);


        double ra = Math.tan(Math.PI * 0.25 + (lat) * DEGRAD * 0.5);
        ra = re * sf / Math.pow(ra, sn);
        double theta = lon * DEGRAD - olon;
        if ( theta > Math.PI) theta -= 2.0 * Math.PI;
        if ( theta < -Math.PI) theta += 2.0 * Math.PI;
        theta *= sn;
        double x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
        double y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);

        grid[0] = (int)x;
        grid[1] = (int)y;
        return grid;
    }
}
