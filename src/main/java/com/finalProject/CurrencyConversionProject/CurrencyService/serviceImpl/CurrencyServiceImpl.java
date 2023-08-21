package com.finalProject.CurrencyConversionProject.CurrencyService.serviceImpl;

import com.finalProject.CurrencyConversionProject.CurrencyService.CurrencyServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class CurrencyServiceImpl implements CurrencyServiceInterface {
    private final WebClient webClient;

    public CurrencyServiceImpl() {
        this.webClient = WebClient.create("http://localhost:8080");
    }

    @Override
    public String convertAmount(String base, String target, Double amount){
        String  result = webClient.get()
                .uri("https://v6.exchangerate-api.com/v6/43dddbf3a919062490e97186/pair/"+ base + "/"+target+"/"+amount.toString())
                .retrieve()
                .bodyToMono(String.class).block();

        return result;
    }
}
