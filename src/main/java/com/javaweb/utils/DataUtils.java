package com.javaweb.utils;

public class DataUtils {
    public static boolean checkData(String data) {
        if(data == null || data.equals("")) return false;
        else return true;
    }
}
