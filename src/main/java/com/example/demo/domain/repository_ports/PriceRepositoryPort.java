package com.example.demo.domain.repository_ports;

import com.example.demo.domain.models.Price;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepositoryPort {

    List<Price> findByBrandIdAndProductIdAndApplicationDate(Long brandId, Long productId, LocalDateTime applicationDate);
}
