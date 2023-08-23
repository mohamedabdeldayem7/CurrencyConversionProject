package com.finalProject.CurrencyConversionProject.CurrencyService;

import java.util.List;

public interface CurrencyServiceInterface {
    Object convertAmount(String base, String target, Double amount);
    Object compareCurrencies(List<String> currencies,String base);
}
