package com.finalProject.CurrencyConversionProject.services.apiService.apiServiceImpl;

import com.finalProject.CurrencyConversionProject.dto.AmountConversionDto;
import com.finalProject.CurrencyConversionProject.dto.FavoriteCurrenciesDto;
import com.finalProject.CurrencyConversionProject.dto.PairCurrenciesConversionDto;
import com.finalProject.CurrencyConversionProject.services.apiService.CurrenncyApiServiceInterface;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
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
        this.webClient = WebClient.create();
        gson = new Gson();
    }
    @Override
    @Cacheable(value = "convertAmountCache", key="#root.methodName+'['+#base+','+#target+']'")
    public PairCurrenciesConversionDto convertAmount(String base, String target, Double amount) {

        //String url = baseUrl + accessKey + "/pair/" + base + "/" + target + "/" + amount.toString();

        String url = baseUrl + accessKey + "/pair/" + base + "/" + target;

        String  jsonResponse = getResponse(url);

        //AmountConversionDto finalresponse = gson.fromJson(jsonResponse, AmountConversionDto.class);
        PairCurrenciesConversionDto finalresponse = gson.fromJson(jsonResponse, PairCurrenciesConversionDto.class);

        return finalresponse;
    }

    @Override
    @Cacheable(value = "compareCurrenciesCache", key = "#base")
    public FavoriteCurrenciesDto compareCurrencies(String base) {
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
