package com.example.demo.infraestructure.adapter_h2db.repository_adapter;

import com.example.demo.domain.models.Price;
import com.example.demo.domain.models.PriceSearchGroup;
import com.example.demo.domain.repository_ports.PriceRepositoryPort;
import com.example.demo.infraestructure.adapter_h2db.daos.PriceDao;
import com.example.demo.infraestructure.adapter_h2db.entities.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PriceRepositoryH2Adapter implements PriceRepositoryPort {

    PriceDao priceDao;

    @Autowired
    public PriceRepositoryH2Adapter(PriceDao priceDao) {
        this.priceDao = priceDao;
    }

    @Override
    public List<Price> findCurrentPrice(PriceSearchGroup priceSearchGroup) {
        return priceDao
                .findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(priceSearchGroup.getBrandId(), priceSearchGroup.getProductId(), priceSearchGroup.getApplicationDate(), priceSearchGroup.getApplicationDate())
                .stream()
                .map(PriceEntity::toPrice)
                .toList();
    }
}
