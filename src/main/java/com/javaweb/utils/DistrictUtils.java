package com.javaweb.utils;

public class DistrictUtils {
    public static String checkDistrict(String data) {
        if(DataUtils.checkData(data)) {
            if(data.equals("QUAN_1")) return "Quận 1";
            else if(data.equals("QUAN_2")) return "Quận 2";
            else if(data.equals("QUAN_3")) return "Quận 3";
            else if(data.equals("QUAN_4")) return "Quận 4";
            else if(data.equals("QUAN_5")) return "Quận 5";
            else if(data.equals("QUAN_6")) return "Quận 6";
        }
        return null;
    }
}
