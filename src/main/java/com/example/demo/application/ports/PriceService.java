package com.example.demo.application.ports;

import com.example.demo.domain.models.Price;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface PriceService {

    Price findCurrentPrice(Long brandId, Long productId, LocalDateTime applicationDate);
}
