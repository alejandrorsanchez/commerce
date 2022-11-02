package com.example.demo.infraestructure.adapter_api.resources;

import com.example.demo.application.use_cases.FindCurrentPriceUseCase;
import com.example.demo.domain.models.PriceSearchGroup;
import com.example.demo.infraestructure.adapter_api.dtos.PriceDTO;
import io.swagger.v3.oas.annotations.Parameter;
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

    private FindCurrentPriceUseCase findCurrentPriceUseCase;

    @Autowired
    public PriceResource(FindCurrentPriceUseCase findCurrentPriceUseCase) {
        this.findCurrentPriceUseCase = findCurrentPriceUseCase;
    }

    @GetMapping(SEARCH)
    public PriceDTO findCurrentPrice(@RequestParam Long brandId,
                                     @RequestParam Long productId,
                                     @Parameter(description = "format: yyyy-MM-dd HH:mm:ss") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate){
        PriceSearchGroup priceSearchGroup = PriceSearchGroup.builder()
                                                            .brandId(brandId)
                                                            .productId(productId)
                                                            .applicationDate(applicationDate)
                                                            .build();
        return new PriceDTO(findCurrentPriceUseCase.findCurrentPrice(priceSearchGroup));
    }

}
