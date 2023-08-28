package com.finalProject.CurrencyConversionProject.model.constants;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter

public enum Currencies {
    EUR("EUR","Europe Union","https://flagcdn.com/32x24/eu.png"),
    USD("USD","United States","https://flagsapi.com/US/flat/64.png"),
    GBP("GBP","England","https://flagsapi.com/GB/flat/64.png"),
    AED("AED","The United Arab Emirates","https://flagsapi.com/AE/flat/64.png"),
    BHD("BHD","Bahrain","https://flagsapi.com/BH/flat/64.png"),
    JPY("JPY","Japan","https://flagsapi.com/JP/flat/64.png"),
    KWD("KWD","Kuwait","https://flagsapi.com/KW/flat/64.png"),
    OMR("OMR","Oman","https://flagsapi.com/OM/flat/64.png"),
    QAR("QAR","QATARI","https://flagsapi.com/QA/flat/64.png"),
    SAR("SAR","Saudi","https://flagsapi.com/SA/flat/64.png"),
    EGP("EGP","EGYPT","https://flagsapi.com/EG/flat/64.png")
    ;

    private String code;
    private String name;
    private String flagUrl;

     Currencies(String code, String name, String flagUrl) {
        this.code = code;
        this.name = name;
        this.flagUrl = flagUrl;
    }

    public static List<Map<String, String>> getCurrencies() {
        List<Map<String, String>> list = new ArrayList<>();
        for (Currencies currency : values()) {
            Map<String, String> map = new HashMap<>();
            map.put("code", currency.getCode());
            map.put("name", currency.getName());
            map.put("flagUrl", currency.getFlagUrl());
            list.add(map);
        }
        return list;
    }

}




