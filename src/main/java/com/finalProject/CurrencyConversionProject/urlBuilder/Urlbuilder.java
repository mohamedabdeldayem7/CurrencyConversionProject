package com.finalProject.CurrencyConversionProject.urlBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Urlbuilder {
    public static String buildURLWithList(String baseUrl, List<String> list) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        if (!list.isEmpty()) {
            for (String value : list) {
                String encodedValue = URLEncoder.encode(value, StandardCharsets.UTF_8);
                urlBuilder.append(encodedValue).append(",");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }

        return urlBuilder.toString();
    }
}
