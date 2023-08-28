package com.finalProject.CurrencyConversionProject.integrationTesting;

import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.PairCurrenciesConversionDto;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class CurrencyControllerTest {
//    @LocalServerPort
//    private Integer port;
//
//    private String baseUrl = "http://localhost";
//
//    private static Gson gson;
//    private static WebClient webClient;
//
//
//    @BeforeAll
//    public static void init(){
//        webClient = WebClient.create();
//        gson = new Gson();
//    }
//    @BeforeEach
//    public void setUp() {
//        baseUrl = baseUrl.concat(":").concat("8080");
//    }
//
//    @Test
//    public void testConvertAmount(){
//        String uri = baseUrl.concat("/pair-conversion");
//
//        String base = "USD";
//        String target = "USD";
//        Double amount = 1.0;
//        String jsonResponse = webClient.get()
//                .uri(uri + "?amount=" + amount,base, target)
//                .retrieve()
//                .bodyToMono(String.class).block();
//        AmountConversionDto response = gson.fromJson(jsonResponse, AmountConversionDto.class);
//
//        AmountConversionDto expectedResponse = AmountConversionDto.builder()
//                .conversion_result(1.0).build();
//
//        Assertions.assertThat(response.getConversion_result()).isEqualTo(expectedResponse.getConversion_result());
//    }
}
