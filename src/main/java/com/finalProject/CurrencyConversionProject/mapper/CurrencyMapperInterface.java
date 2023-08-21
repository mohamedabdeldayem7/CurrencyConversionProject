package com.finalProject.CurrencyConversionProject.mapper;

import com.finalProject.CurrencyConversionProject.Dto.CurrencyDto;
import com.finalProject.CurrencyConversionProject.model.entities.Currency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapperInterface {
    Currency currencyDtoToCurrency(CurrencyDto currencyDto);
    CurrencyDto currencyToCurrencyDto(Currency currency);
}
