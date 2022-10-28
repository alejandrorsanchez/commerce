package com.example.demo.infraestructure.adapter_h2db.seeder;

import com.example.demo.infraestructure.adapter_h2db.daos.PriceDao;
import com.example.demo.infraestructure.adapter_h2db.entities.PriceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class H2dbSeeder {
    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 35455L;
    private static final String EUR = "EUR";

    @Autowired
    private PriceDao  priceDao;

    public void populateDB(){
        PriceEntity priceEntity1 = PriceEntity.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .priority(0)
                .priceValue(BigDecimal.valueOf(35.50))
                .currency(EUR)
                .build();
        PriceEntity priceEntity2 = PriceEntity.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
                .priceList(2)
                .priority(1)
                .priceValue(BigDecimal.valueOf(25.45))
                .currency(EUR)
                .build();
        PriceEntity priceEntity3 = PriceEntity.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                .priceList(3)
                .priority(1)
                .priceValue(BigDecimal.valueOf(30.50))
                .currency(EUR)
                .build();
        PriceEntity priceEntity4 = PriceEntity.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(4)
                .priority(1)
                .priceValue(BigDecimal.valueOf(38.95))
                .currency(EUR)
                .build();
        this.priceDao.saveAll(List.of(priceEntity1, priceEntity2, priceEntity3, priceEntity4));
    }

    public void tearDown(){
        this.priceDao.deleteAll();
    }

}
