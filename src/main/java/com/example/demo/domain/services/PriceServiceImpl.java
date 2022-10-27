package com.example.demo.domain.services;

import com.example.demo.application.primary_ports.PriceService;
import com.example.demo.domain.models.Price;
import com.example.demo.domain.repository_ports.PriceRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepositoryPort priceRepositoryPort;

    @Override
    public Price findCurrentPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceRepositoryPort.findByBrandIdAndProductIdAndApplicationDate(brandId, productId, applicationDate)
                .stream()
                .sorted(Comparator.comparing(Price::getStartDate).reversed())
                .findFirst()
                .orElseThrow();
    }
}
