package com.example.demo.infraestructure.adapter_h2db.entities;

import com.example.demo.domain.models.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prices")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long brandId;

    @NotNull
    private Long productId;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime endDate;

    @NotNull
    private Integer priceList;

    @NotNull
    private Integer priority;

    @NotNull
    private BigDecimal priceValue;

    @NotNull
    @NotBlank
    private String currency;

    public Price toPrice() {
        return Price.builder()
                .brandId(this.brandId)
                .productId(this.productId)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .priceList(this.priceList)
                .priority(this.priority)
                .priceValue(this.priceValue)
                .currency(this.currency)
                .build();
    }
}
