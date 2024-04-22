package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransactionType {
    CSKH("Chăm Sóc Khách Hàng"),
    DDX("Dẫn Đi Xem");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public static Map<String, String> transactionType() {
        Map<String, String> mp = new HashMap<>();
        for(TransactionType it : TransactionType.values()) {
            mp.put(it.toString(), it.name);
        }
        return mp;
    }
}
