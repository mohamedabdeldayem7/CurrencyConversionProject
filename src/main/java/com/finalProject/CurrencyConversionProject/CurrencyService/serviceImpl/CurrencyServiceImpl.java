package com.finalProject.CurrencyConversionProject.CurrencyService.serviceImpl;

import com.finalProject.CurrencyConversionProject.CurrencyService.CurrencyServiceInterface;
import com.finalProject.CurrencyConversionProject.validation.InputValidation;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;



@Service
public class CurrencyServiceImpl implements CurrencyServiceInterface {
    @Autowired
    InputValidation inputValidation;
    @Value("${api.accessKey}")
    private String accessKey;
    private final WebClient webClient;
//comment
    public CurrencyServiceImpl() {
        this.webClient = WebClient.create("http://localhost:8080");
    }

    @Override
    public Object convertAmount(String base, String target, Double amount){
        this.inputValidation.checkCurrency(base);
        this.inputValidation.checkCurrency(target);
        this.inputValidation.checkAmount(amount);

        String url = "https://v6.exchangerate-api.com/v6/" + accessKey
                +"/pair/"+ base + "/" + target + "/" + amount.toString();

        String  response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class).block();

        Object responseObject = this.convertToJsonObject(response);
        return responseObject;
    }

    private Object convertToJsonObject(String response){
        String json = response;

        Gson gson = new Gson();
        Object convertedObject = gson.fromJson(json, Object.class);

        return convertedObject;
    }

}
