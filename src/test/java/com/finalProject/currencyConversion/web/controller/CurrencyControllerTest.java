package com.finalProject.currencyConversion.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalProject.currencyConversion.dto.AmountConversionDto;
import com.finalProject.currencyConversion.dto.TwoCurrenciesComparisonDto;
import com.finalProject.currencyConversion.model.constants.Currencies;
import com.finalProject.currencyConversion.web.response.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

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

    @DisplayName("JUnit test for convertAmount method")
    @Test
    void givenBaseAndTargetAndAmount_thenReturnAmountConversionDto() throws Exception {
        String url = "/pair-conversion";

        AmountConversionDto amountConversionDto = AmountConversionDto.builder()
                .conversion_result(1.0).build();

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
    void ReturnCurrenciesList() throws Exception {
        String url = "/currencies";
        List<Map<String, String>> currencies = Currencies.getCurrencies();

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

    @DisplayName("JUnit test for getConversionRates method")
    @Test
    void givenBaseAndListOfFavoriteCurrencies_thenReturnFavoriteCurrenciesDto() throws Exception {
        String url = "/favorite-currencies";
        String base = "USD";
        List<String> favorites = Arrays.asList("USD", "USD");
        List<Double> expected = Arrays.asList(1.0, 1.0);
        ResponseModel<?> responseModel = ResponseModel.builder()
                .statusCode(200)
                .status("success")
                .data(expected)
                .build();

        String finalResponse = mapper.writeValueAsString(responseModel);

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .content(mapper.writeValueAsString(favorites))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("base", base)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(finalResponse));
    }

    @DisplayName("JUnit test for compareTwoCurrencies method")
    @Test
    void givenBaseAndAmountAndTarget1AndTarget2_thenReturnTwoCurrenciesComparisonDto() throws Exception {
        String url = "/comparison";
        AmountConversionDto amountConversionDto1 = AmountConversionDto.builder()
                .conversion_result(1.0).build();
        AmountConversionDto amountConversionDto2 = AmountConversionDto.builder()
                .conversion_result(1.0).build();
        TwoCurrenciesComparisonDto currenciesComparisonDto = TwoCurrenciesComparisonDto.builder()
                .firstTargetCurrency(amountConversionDto1)
                .secondTargetCurrency(amountConversionDto2)
                .build();

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