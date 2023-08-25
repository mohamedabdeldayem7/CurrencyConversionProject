package com.finalProject.CurrencyConversionProject.repository;

import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;

public interface CurrencyRepository {
    AmountConversionDto convertAmount(String base, String target, Double amount);
    Object compareCurrencies(String base);
}
