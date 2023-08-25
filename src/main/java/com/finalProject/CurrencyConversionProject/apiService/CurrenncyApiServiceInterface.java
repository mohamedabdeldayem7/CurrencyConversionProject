package com.finalProject.CurrencyConversionProject.apiService;

import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;

public interface CurrenncyApiServiceInterface {
    AmountConversionDto convertAmount(String base, String target, Double amount);
    Object compareCurrencies(String base);
}
