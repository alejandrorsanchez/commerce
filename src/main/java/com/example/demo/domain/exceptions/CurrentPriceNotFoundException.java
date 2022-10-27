package com.example.demo.domain.exceptions;

import java.time.LocalDateTime;

public class CurrentPriceNotFoundException extends NotFoundException{

    public CurrentPriceNotFoundException(Long brandId, Long productId, LocalDateTime applicationDate){
        super("A price for the product " + productId + " from the brand " + brandId + " at " + applicationDate + " could not be found");
    }

}
