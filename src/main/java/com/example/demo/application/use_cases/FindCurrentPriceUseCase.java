package com.example.demo.application.use_cases;

import com.example.demo.domain.models.Price;
import com.example.demo.domain.models.PriceSearchGroup;
import org.springframework.stereotype.Service;

@Service
public interface FindCurrentPriceUseCase {
    Price findCurrentPrice(PriceSearchGroup priceSearchGroup);

}
