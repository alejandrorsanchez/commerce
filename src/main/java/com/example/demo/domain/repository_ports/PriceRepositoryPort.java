package com.example.demo.domain.repository_ports;

import com.example.demo.domain.models.Price;
import com.example.demo.domain.models.PriceSearchGroup;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PriceRepositoryPort {

    List<Price> findCurrentPrice(PriceSearchGroup priceSearchGroup);
}
