package com.finalProject.currencyConversion.services.api.service;

import com.finalProject.currencyConversion.dto.FavoriteCurrenciesDto;
import com.finalProject.currencyConversion.dto.PairCurrenciesConversionDto;

public interface CurrenncyApiServiceInterface {
    PairCurrenciesConversionDto getConversionRate(String base, String target);

    FavoriteCurrenciesDto compareCurrencies(String base);
}
