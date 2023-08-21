package com.finalProject.CurrencyConversionProject.web.controller;

import com.finalProject.CurrencyConversionProject.CurrencyService.serviceImpl.CurrencyServiceImpl;
import com.finalProject.CurrencyConversionProject.web.response.ResponseModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {
    @Autowired
    private CurrencyServiceImpl currencyService;

    @GetMapping("/{base}/{target}/{amount}")
    public ResponseEntity<ResponseModel<?>> getCurrency(@PathVariable("base") String base,
                                                        @PathVariable("target") String target,
                                                        @PathVariable("amount") Double amount)  {
        String json = currencyService.convertAmount(base, target, amount);

        Gson gson = new Gson();
        Object currency = gson.fromJson(json, Object.class);

        ResponseModel<Object> model = ResponseModel.<Object>builder()
                .data(currency).statusCode(HttpStatus.OK.value()).build();

        return new ResponseEntity(model, HttpStatus.OK);
    }
}
