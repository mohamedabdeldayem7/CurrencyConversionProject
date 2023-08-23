package com.finalProject.CurrencyConversionProject.CurrencyService;

import com.finalProject.CurrencyConversionProject.model.constants.Currencies;

import java.util.List;
import java.util.Map;

public interface CurrencyServiceInterface {
    Object convertAmount(String base, String target, Double amount);
    Object compareCurrencies(List<String> currencies,String base);
    List<Map<String, String>> getCurrencies();
}
