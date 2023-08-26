package com.finalProject.CurrencyConversionProject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@Getter
public class TwoCurrenciesComparisonDto implements Serializable {
    private AmountConversionDto firstTargetCurrency;
    private AmountConversionDto secondTargetCurrency;
}
