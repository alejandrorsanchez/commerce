package com.example.demo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceSearchGroup {
    private Long brandId;
    private Long productId;
    private LocalDateTime applicationDate;
}
