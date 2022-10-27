package com.example.demo.infraestructure.adapter_api.resources;

import com.example.demo.application.primary_ports.PriceService;
import com.example.demo.infraestructure.adapter_api.dtos.PriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(PriceResource.PRICES)
public class PriceResource {

    public static final String PRICES = "/prices";
    public static final String SEARCH = "/search";

    @Autowired
    private PriceService priceService;

    @GetMapping(SEARCH)
    public PriceDTO findCurrentPrice(@RequestParam Long brandId,
                                     @RequestParam Long productId,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate){
        return new PriceDTO(priceService.findCurrentPrice(brandId, productId, applicationDate));
    }

}
