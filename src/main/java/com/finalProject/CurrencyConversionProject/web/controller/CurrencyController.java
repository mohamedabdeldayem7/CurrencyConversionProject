package com.finalProject.CurrencyConversionProject.web.controller;

import com.finalProject.CurrencyConversionProject.CurrencyService.serviceImpl.CurrencyServiceImpl;
import com.finalProject.CurrencyConversionProject.web.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CurrencyController {
    @Autowired
    private CurrencyServiceImpl currencyService;


    @GetMapping("/{base}/{target}/{amount}")
    public ResponseEntity<ResponseModel<?>> convertAmount(@PathVariable("base") String base,
                                                        @PathVariable("target") String target,
                                                        @PathVariable("amount") Double amount)  {

        Object response =  this.currencyService.convertAmount(base, target, amount);
        ResponseModel<Object> model = ResponseModel.<Object>builder()
                .data(response).statusCode(HttpStatus.OK.value()).build();

        return new ResponseEntity(model, HttpStatus.OK);
    }
    @GetMapping("/{currencies}/{base}")
    public ResponseEntity<ResponseModel<?>> comparingCurrencies(@PathVariable("currencies" )List<String>currencies,
                                                                @PathVariable("base") String base){
        Object response =  this.currencyService.compareCurrencies(currencies,base);
        ResponseModel<Object> model = ResponseModel.<Object>builder()
                .data(response).statusCode(HttpStatus.OK.value()).build();

        return new ResponseEntity(model, HttpStatus.OK);

    }

    @GetMapping("/{base}/{amount}/{target1}/{target2}")
    public ResponseEntity<ResponseModel<?>> convertAmount(@PathVariable("base") String base,
                                                          @PathVariable("amount") Double amount,
                                                          @PathVariable("target") String target1,
                                                          @PathVariable("amount") String target2)  {

        Object response =  this.currencyService.compareTwoCurrencies(base,amount, target1, target2);
        ResponseModel<Object> model = ResponseModel.<Object>builder()
                .data(response).statusCode(HttpStatus.OK.value()).build();

        return new ResponseEntity(model, HttpStatus.OK);
    }
    @GetMapping("/currencies")
    public ResponseEntity<ResponseModel<?>> getCurrencies(){
        List<Map<String, String>> currencies =  this.currencyService.getCurrencies();
        ResponseModel<List<Map<String, String>>> model = ResponseModel.<List<Map<String, String>>>builder()
                .data(currencies).statusCode(HttpStatus.OK.value()).build();
        return new ResponseEntity(model, HttpStatus.OK);
    }
}
