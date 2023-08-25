package com.finalProject.CurrencyConversionProject.apiService.apiServiceImpl;

import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.FavoriteCurrenciesDto;
import com.finalProject.CurrencyConversionProject.apiService.CurrenncyApiServiceInterface;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyApiServiceImpl implements CurrenncyApiServiceInterface {
    @Value("${api.accessKey}")
    private String accessKey;
    @Value("${base_url}")
    private String baseUrl;

    private Gson gson;
    private final WebClient webClient;

    public CurrencyApiServiceImpl() {
        this.webClient = WebClient.create("");
        gson = new Gson();
    }
    @Override
    public AmountConversionDto convertAmount(String base, String target, Double amount) {

        String url = baseUrl + accessKey + "/pair/" + base + "/" + target + "/" + amount.toString();

        String  jsonResponse = getResponse(url);

        AmountConversionDto finalresponse = gson.fromJson(jsonResponse, AmountConversionDto.class);

        return finalresponse;
    }

    @Override
    public Object compareCurrencies(String base) {
        String url = baseUrl + accessKey + "/latest/" + base;

        String  jsonResponse = getResponse(url);
        FavoriteCurrenciesDto finalresponse = gson.fromJson(jsonResponse, FavoriteCurrenciesDto.class);

        return finalresponse;
    }

    private String getResponse(String url){
        String  response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class).block();
        return response;
    }

}
