package com.finalProject.CurrencyConversionProject.mapper;

import com.finalProject.CurrencyConversionProject.Dto.UserDto;
import com.finalProject.CurrencyConversionProject.model.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UserMapperInterface {
    User currencyDtoToCurrency(UserDto currencyDto);
    UserDto currencyToCurrencyDto(User currency);
}
