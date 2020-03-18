package com.mygdx.puttingame;

public class Tools {
    public static double advRound(double num, int dec){
        num = num * Math.pow(10,dec);
        num = Math.round(num);
        num = num / Math.pow(10,dec);
        return num;
    }

    public static Vector2d velFromAngle(double a, double v){
        a = Math.toRadians(a);
        double vy = v * Math.cos(a);
        double vx = v * Math.sin(a);
        return new Vector2d(vx,vy);
    }
}
