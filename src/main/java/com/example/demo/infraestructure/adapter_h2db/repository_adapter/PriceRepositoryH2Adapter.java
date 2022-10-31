package com.example.demo.infraestructure.adapter_h2db.repository_adapter;

import com.example.demo.domain.models.Price;
import com.example.demo.domain.repository_ports.PriceRepositoryPort;
import com.example.demo.infraestructure.adapter_h2db.daos.PriceDao;
import com.example.demo.infraestructure.adapter_h2db.entities.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceRepositoryH2Adapter implements PriceRepositoryPort {

    @Autowired
    PriceDao priceDao;

    @Override
    public List<Price> findByBrandIdAndProductIdAndApplicationDate(Long brandId, Long productId, LocalDateTime applicationDate) {
        return priceDao
                .findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(brandId, productId, applicationDate, applicationDate)
                .stream()
                .map(PriceEntity::toPrice)
                .toList();
    }
}
