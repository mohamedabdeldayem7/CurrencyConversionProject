package com.finalProject.currencyConversion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoriteCurrenciesDto implements Serializable {
    Map<String, Double> conversion_rates;
}
