package com.example.demo.domain.exceptions;

import com.example.demo.domain.models.PriceSearchGroup;

public class CurrentPriceNotFoundException extends NotFoundException{

    public CurrentPriceNotFoundException(PriceSearchGroup priceSearchGroup){
        super("A price for the product " + priceSearchGroup.getProductId() + " from the brand " + priceSearchGroup.getBrandId() + " at " + priceSearchGroup.getApplicationDate() + " could not be found");
    }

}
