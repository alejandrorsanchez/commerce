package com.example.demo.domain.services;

import com.example.demo.application.use_cases.FindCurrentPriceUseCase;
import com.example.demo.domain.exceptions.CurrentPriceNotFoundException;
import com.example.demo.domain.models.Price;
import com.example.demo.domain.models.PriceSearchGroup;
import com.example.demo.domain.repository_ports.PriceRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class PriceServiceImpl implements FindCurrentPriceUseCase {

    private PriceRepositoryPort priceRepositoryPort;

    @Autowired
    public PriceServiceImpl(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price findCurrentPrice(PriceSearchGroup priceSearchGroup) {
        return priceRepositoryPort.findCurrentPrice(priceSearchGroup)
                .stream()
                .sorted(Comparator.comparing(Price::getStartDate).reversed())
                .findFirst()
                .orElseThrow(() -> new CurrentPriceNotFoundException(priceSearchGroup));
    }
}
