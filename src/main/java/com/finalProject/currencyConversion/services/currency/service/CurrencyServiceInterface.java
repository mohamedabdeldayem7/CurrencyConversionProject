package com.finalProject.currencyConversion.services.currency.service;

import com.finalProject.currencyConversion.dto.AmountConversionDto;
import com.finalProject.currencyConversion.dto.TwoCurrenciesComparisonDto;

import java.util.List;
import java.util.Map;

public interface CurrencyServiceInterface {
    AmountConversionDto convertAmount(String base, String target, Double amount);

    List<Double> compareCurrencies(List<String> currencies, String base);

    List<Map<String, String>> getCurrencies();

    TwoCurrenciesComparisonDto compareTwoCurrencies(String base, Double amount, String target1, String target2);
}
