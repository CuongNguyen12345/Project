package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;
public enum StatusType {
    DANG_XU_LY("Đang xử lý"),
    DA_XU_LY("Đã xử lý"),
    CHUA_XU_LY("Chưa xử lý");

    private final String name;

    StatusType(String name) {
        this.name = name;
    }

    public static Map<String, String> statusType() {
        Map<String, String> mp = new HashMap<>();
        for(StatusType st : StatusType.values()) {
            mp.put(st.toString(), st.name);
        }
        return mp;
    }
}
