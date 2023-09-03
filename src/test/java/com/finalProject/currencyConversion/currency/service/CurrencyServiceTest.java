package com.finalProject.currencyConversion.currency.service;

import com.finalProject.currencyConversion.dto.PairCurrenciesConversionDto;
import com.finalProject.currencyConversion.exception.InvalidInputException;
import com.finalProject.currencyConversion.model.constants.Messages;
import com.finalProject.currencyConversion.services.CacheService;
import com.finalProject.currencyConversion.services.api.service.CurrenncyApiServiceInterface;
import com.finalProject.currencyConversion.services.currency.service.CurrencyServiceImpl;
import com.finalProject.currencyConversion.dto.AmountConversionDto;
import com.finalProject.currencyConversion.dto.FavoriteCurrenciesDto;
import com.finalProject.currencyConversion.dto.TwoCurrenciesComparisonDto;
import com.finalProject.currencyConversion.model.constants.Currencies;
import com.finalProject.currencyConversion.validation.InputValidation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


class CurrencyServiceTest {
    @Mock
    private CurrenncyApiServiceInterface currenncyApiService;
    @Mock
    private InputValidation inputValidation;
    @Mock
    private CacheService cacheService;
    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("JUnit test for convertAmount method")
    @Test
    void givenBaseAndTargetAndAmount_whenConvertAmount_thenReturnAmountConversionDto() {
        String base = "USD";
        String target = "USD";
        Double amount = 1.0;
        Double conversionResult = 1.0;
        Double conversionRate = 1.0;
        PairCurrenciesConversionDto pairCurrenciesConversionDto = PairCurrenciesConversionDto.builder()
                .base_code(base)
                .target_code(target)
                .conversion_rate(conversionRate).build();
        AmountConversionDto amountConversionDto = AmountConversionDto.builder()
                .conversion_result(conversionResult).build();

        when(currenncyApiService.getConversionRate(base, target)).thenReturn(pairCurrenciesConversionDto);

        AmountConversionDto response = this.currencyService.convertAmount(base, target, amount);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getConversion_result()).isEqualTo(amountConversionDto.getConversion_result());
    }

    @DisplayName("JUnit test for InvalidConvertAmount method")
    @Test()
    void givenInvalidBaseOrInvalidTargetAndAmount_whenConvertAmount_thenTrowInvalidInputException() {
        String base = "gsu";
        String target = "vsl";
        Double amount = 1.0;
        InvalidInputException invalidInputException = new InvalidInputException(Messages.UNSUPPORTED_CURRENCY);

        when(currenncyApiService.getConversionRate(base, target)).thenThrow(invalidInputException);

        InvalidInputException invalidInputException1 = org.junit.jupiter.api.Assertions.assertThrows(InvalidInputException.class,
                () -> currencyService.convertAmount(base, target, amount));

        String expectedMessage = Messages.UNSUPPORTED_CURRENCY;
        String actualMessage = invalidInputException1.getMessage();

        org.junit.jupiter.api.Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @DisplayName("JUnit test for InvalidConvertAmount method")
    @Test()
    void givenBaseAndTargetAndInvalidAmount_whenConvertAmount_thenTrowInvalidInputException() {
        String base = "USD";
        String target = "USd";
        Double amount = -1.0;
        InvalidInputException invalidInputException = new InvalidInputException(Messages.INVALID_AMOUNT);

        when(currenncyApiService.getConversionRate(base, target)).thenThrow(invalidInputException);

        InvalidInputException invalidInputException1 = org.junit.jupiter.api.Assertions.assertThrows(InvalidInputException.class,
                () -> currencyService.convertAmount(base, target, amount));

        String expectedMessage = Messages.INVALID_AMOUNT;
        String actualMessage = invalidInputException1.getMessage();

        org.junit.jupiter.api.Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @DisplayName("JUnit test for compareCurrencies method")
    @Test
    void givenBaseAndListOfFavoriteCurrencies_whenCompareCurrencies_thenReturnFavoriteCurrenciesDto() {

        List<String> favorites = Arrays.asList("USD", "USD");

        Map<String, Double> conversionRates = new HashMap<>();
        conversionRates.put(favorites.get(0), 1.0);
        conversionRates.put(favorites.get(1), 1.0);

        FavoriteCurrenciesDto favoriteCurrenciesDto = FavoriteCurrenciesDto.builder()
                .conversion_rates(conversionRates).build();

        List<Double> expected = Arrays.asList(1.0, 1.0);

        when(currenncyApiService.compareCurrencies("USD")).thenReturn(favoriteCurrenciesDto);

        List<Double> response = this.currencyService.compareCurrencies(favorites, "USD");

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).isEqualTo(expected);

    }

    @Test
    @DisplayName("JUnit test for getCurrencies method")
    void givenCurrenciesList_whenGetCurrencies_thenReturnCurrenciesList() {
        List<Map<String, String>> expectedResponse = Currencies.getCurrencies();
        List<Map<String, String>> response = this.currencyService.getCurrencies();
        assertThat(response).isEqualTo(expectedResponse);
    }

    @DisplayName("JUnit test for compareTwoCurrencies method")
    @Test
    void givenBaseAndAmountAndTarget1AndTarget2_whenConvertAmount_thenReturnTwoCurrenciesComparisonDto() {
        String base = "USD";
        Double amount = 1.0;
        String target1 = "USD";
        String target2 = "USD";
        Double conversionResult = 1.0;
        Double conversionRate = 1.0;
        PairCurrenciesConversionDto firstPairCurrenciesConversion = PairCurrenciesConversionDto.builder()
                .base_code(base)
                .target_code(target1)
                .conversion_rate(conversionRate).build();
        PairCurrenciesConversionDto secondPairCurrenciesConversion = PairCurrenciesConversionDto.builder()
                .base_code(base)
                .target_code(target2)
                .conversion_rate(conversionRate).build();
        AmountConversionDto firstTargetCurrency = AmountConversionDto.builder()
                .conversion_result(conversionResult).build();
        AmountConversionDto secondTargetCurrency = AmountConversionDto.builder()
                .conversion_result(conversionResult).build();
        TwoCurrenciesComparisonDto expectedResponse = TwoCurrenciesComparisonDto.builder()
                .firstTargetCurrency(firstTargetCurrency)
                .secondTargetCurrency(secondTargetCurrency).build();

        when(currenncyApiService.getConversionRate(base, target1)).thenReturn(firstPairCurrenciesConversion);
        when(currenncyApiService.getConversionRate(base, target2)).thenReturn(secondPairCurrenciesConversion);

        TwoCurrenciesComparisonDto response = this.currencyService.compareTwoCurrencies(base, amount, target1, target2);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getFirstTargetCurrency().getConversion_result()).isEqualTo(firstTargetCurrency.getConversion_result());
        Assertions.assertThat(response.getSecondTargetCurrency().getConversion_result()).isEqualTo(secondTargetCurrency.getConversion_result());
    }

}