package com.finalProject.currencyConversion.model.constants;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter

public enum Currencies {
    EUR("EUR", "Europe Union", "https://flagcdn.com/w80/eu.png"),
    USD("USD", "United States", "https://flagcdn.com/w80/us.png"),
    GBP("GBP", "England", "https://flagcdn.com/w80/gb.png"),
    AED("AED", "The United Arab Emirates", "https://flagcdn.com/w80/ae.png"),
    BHD("BHD", "Bahrain", "https://flagcdn.com/w80/bh.png"),
    JPY("JPY", "Japan", "https://flagcdn.com/w80/jp.png"),
    KWD("KWD", "Kuwait", "https://flagcdn.com/w80/kw.png"),
    OMR("OMR", "Oman", "https://flagcdn.com/w80/om.png"),
    QAR("QAR", "QATARI", "https://flagcdn.com/w80/qa.png"),
    SAR("SAR", "Saudi", "https://flagcdn.com/w80/sa.png"),
    EGP("EGP", "EGYPT", "https://flagcdn.com/w80/eg.png");

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




