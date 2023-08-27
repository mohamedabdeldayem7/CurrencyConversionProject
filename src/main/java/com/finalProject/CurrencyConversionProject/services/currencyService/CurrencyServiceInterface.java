package com.finalProject.CurrencyConversionProject.services.currencyService;

import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.FavoriteCurrenciesDto;
import com.finalProject.CurrencyConversionProject.dto.TwoCurrenciesComparisonDto;

import java.util.List;
import java.util.Map;

public interface CurrencyServiceInterface {
    AmountConversionDto convertAmount(String base, String target, Double amount);
    FavoriteCurrenciesDto compareCurrencies(List<String> currencies, String base);
    List<Map<String, String>> getCurrencies();
    TwoCurrenciesComparisonDto compareTwoCurrencies(String base, Double amount,String target1,String target2);
}