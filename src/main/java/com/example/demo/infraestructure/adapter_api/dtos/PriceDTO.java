package com.example.demo.infraestructure.adapter_api.dtos;

import com.example.demo.domain.models.Price;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {

    private Long brandId;
    private Long productId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private BigDecimal priceValue;

    public PriceDTO(Price price){
         this.brandId = price.getBrandId();
        this.productId = price.getProductId();
        this.startDate = price.getStartDate();
        this.endDate = price.getEndDate();
        this.priceValue = price.getPriceValue();

    }
}
