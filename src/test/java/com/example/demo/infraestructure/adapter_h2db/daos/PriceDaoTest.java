package com.example.demo.infraestructure.adapter_h2db.daos;

import com.example.demo.infraestructure.adapter_h2db.entities.PriceEntity;
import com.example.demo.infraestructure.adapter_h2db.seeder.H2dbSeeder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:test.properties")
class PriceDaoTest {
    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 35455L;

    @Autowired
    PriceDao priceDao;
    @Autowired
    H2dbSeeder h2dbSeeder;

    @BeforeEach
    void populate(){ h2dbSeeder.populateDB(); }

    @AfterEach
    void tearDown(){ h2dbSeeder.tearDown(); }

    @Test
    void findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThanDay14Hour10Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        List<PriceEntity> prices = priceDao.findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(BRAND_ID, PRODUCT_ID, APPLICATION_DATE, APPLICATION_DATE);
        assertEquals(1, prices.size());
        assertEquals(0, (prices.stream()
                                        .findFirst()
                                        .get()
                                        .getPriceValue()
                                ).compareTo(BigDecimal.valueOf(35.50)));
    }

    @Test
    void findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThanDay14Hour16Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        List<PriceEntity> prices = priceDao.findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(BRAND_ID, PRODUCT_ID, APPLICATION_DATE, APPLICATION_DATE);
        assertEquals(2, prices.size());
    }

    @Test
    void findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThanDay14Hour21Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        List<PriceEntity> prices = priceDao.findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(BRAND_ID, PRODUCT_ID, APPLICATION_DATE, APPLICATION_DATE);
        assertEquals(1, prices.size());
        assertEquals(0, (prices.stream()
                                        .findFirst()
                                        .get()
                                        .getPriceValue()
                                ).compareTo(BigDecimal.valueOf(35.50)));
    }

    @Test
    void findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThanDay15Hour10Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        List<PriceEntity> prices = priceDao.findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(BRAND_ID, PRODUCT_ID, APPLICATION_DATE, APPLICATION_DATE);
        assertEquals(2, prices.size());
    }

    @Test
    void findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThanDay16Hour21Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        List<PriceEntity> prices = priceDao.findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(BRAND_ID, PRODUCT_ID, APPLICATION_DATE, APPLICATION_DATE);
        assertEquals(2, prices.size());
    }

}
