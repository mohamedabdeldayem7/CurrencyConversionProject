package com.finalProject.CurrencyConversionProject.apiService;

import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.FavoriteCurrenciesDto;

public interface CurrenncyApiServiceInterface {
    AmountConversionDto convertAmount(String base, String target, Double amount);
    FavoriteCurrenciesDto compareCurrencies(String base);
}
