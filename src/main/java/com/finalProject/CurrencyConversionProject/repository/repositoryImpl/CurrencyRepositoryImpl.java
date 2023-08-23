package com.finalProject.CurrencyConversionProject.repository.repositoryImpl;

import com.finalProject.CurrencyConversionProject.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class CurrencyRepositoryImpl implements CurrencyRepository {
    @Value("${api.accessKey}")
    private String accessKey;
//    @Value("${api2.accessKey}")
//    private String accessKeyToCompareCurrencies;
    private final WebClient webClient;

    public CurrencyRepositoryImpl() {
        this.webClient = WebClient.create("http://localhost:8080");
    }
    @Override
    public String convertAmount(String base, String target, Double amount) {
        String url = "https://v6.exchangerate-api.com/v6/" + accessKey
                +"/pair/"+ base + "/" + target + "/" + amount.toString();
        String  response = getResponse(url);
        return response;
    }

    @Override
    public String compareCurrencies(String base) {
        String url = "https://v6.exchangerate-api.com/v6/" + accessKey
                +"/latest/"+ base;
        String  response = getResponse(url);
        return response;
    }

    private String getResponse(String url){
        String  response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class).block();
        return response;
    }
}
