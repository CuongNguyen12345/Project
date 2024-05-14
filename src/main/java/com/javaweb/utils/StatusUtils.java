package com.javaweb.utils;

public class StatusUtils {
    public static String checkData(String data) {
        if(DataUtils.checkData(data)){
            if(data.equals("DANG_XU_LY")) return "Đang xử lý";
            else if(data.equals("DA_XU_LY")) return "Đã xử lý";
            else return "Chưa xử lý";
        }
        return null;
    }
}
