package com.finalProject.CurrencyConversionProject.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalProject.CurrencyConversionProject.currencyService.serviceImpl.CurrencyServiceImpl;
import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.TwoCurrenciesComparisonDto;
import com.finalProject.CurrencyConversionProject.model.constants.Currencies;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalProject.CurrencyConversionProject.currencyService.serviceImpl.CurrencyServiceImpl;
import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalProject.CurrencyConversionProject.currencyService.serviceImpl.CurrencyServiceImpl;
import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.FavoriteCurrenciesDto;
import com.finalProject.CurrencyConversionProject.web.response.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Mock
    private CurrencyServiceImpl currencyService;

    @DisplayName("JUnit test for convertAmount method")
    @Test
    void givenBaseAndTargetAndAmount_whenConvertAmount_thenReturnAmountConversionDto() throws Exception {
        String url = "/pair-conversion";

        AmountConversionDto amountConversionDto = AmountConversionDto.builder()
                .conversion_result(1.0).build();

        when(currencyService.convertAmount(anyString(), anyString(), anyDouble())).thenReturn(amountConversionDto);


        ResponseModel<?> responseModel = ResponseModel.builder()
                .statusCode(200)
                .status("success")
                .data(amountConversionDto)
                .build();
        String response = mapper.writeValueAsString(responseModel);

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param("base", "USD")
                        .param("target", "USD")
                        .param("amount", String.valueOf(1.0)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));

    }

    @DisplayName("JUnit test for getCurrencies method")
    @Test
    void givenCurrenciesList_whenGetCurrencies_thenReturnCurrenciesList() throws Exception {
        String url = "/currencies";
        List<Map<String, String>> currencies = Currencies.getCurrencies();

        when(this.currencyService.getCurrencies()).thenReturn(currencies);

        ResponseModel<?> responseModel = ResponseModel.builder()
                .statusCode(200)
                .status("success")
                .data(currencies)
                .build();
        String response = mapper.writeValueAsString(responseModel);

        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @DisplayName("JUnit test for compareCurrencies method")
    @Test
    void givenBaseAndListOfFavoriteCurrencies_whenCompareCurrencies_thenReturnFavoriteCurrenciesDto() throws Exception {
        String url="/favorite-currencies";
        String base = "USD";
        List<String> favorites = Arrays.asList("USD", "USD");
        Map<String, Double> conversionRates = new HashMap<>();
        conversionRates.put(favorites.get(0), 1.0);
        conversionRates.put(favorites.get(1), 1.0);
        FavoriteCurrenciesDto favoriteCurrenciesDto=FavoriteCurrenciesDto.builder()
                .conversion_rates(conversionRates).build();

        when(currencyService.compareCurrencies(favorites,base)).thenReturn(favoriteCurrenciesDto);

        ResponseModel<?> responseModel = ResponseModel.builder()
                .statusCode(200)
                .status("success")
                .data(currenciesComparisonDto)
                .build();
        String response = mapper.writeValueAsString(responseModel);

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .param("base", "USD")
                        .param("target1", "USD")
                        .param("target2", "USD")
                        .param("amount", String.valueOf(1.0)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}