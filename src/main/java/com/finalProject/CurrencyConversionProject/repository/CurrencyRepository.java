package com.finalProject.CurrencyConversionProject.repository;

public interface CurrencyRepository {
    String convertAmount(String base, String target, Double amount);
    String compareCurrencies(String base);
}
