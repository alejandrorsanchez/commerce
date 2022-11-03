package com.example.demo.infraestructure.adapter_api.dtos;

import com.example.demo.domain.models.Price;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDTO priceDTO = (PriceDTO) o;
        return brandId.equals(priceDTO.brandId) && productId.equals(priceDTO.productId) &&
               startDate.equals(priceDTO.startDate) && endDate.equals(priceDTO.endDate) &&
               priceValue.compareTo(priceDTO.priceValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandId, productId, startDate, endDate, priceValue);
    }
}
