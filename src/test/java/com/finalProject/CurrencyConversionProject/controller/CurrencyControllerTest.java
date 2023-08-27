package com.finalProject.CurrencyConversionProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalProject.CurrencyConversionProject.currencyService.serviceImpl.CurrencyServiceImpl;
import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    CurrencyServiceImpl currencyService;
    @DisplayName("JUnit test for convertAmount method")
    @Test
    void givenBaseAndTargetAndAmount_whenConvertAmount_thenReturnAmountConversionDto() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String url = "/pair-conversion";

        AmountConversionDto amountConversionDto = AmountConversionDto.builder()
                .conversion_result(1.0).build();

        when(currencyService.convertAmount(Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble())).thenReturn(amountConversionDto);

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

}