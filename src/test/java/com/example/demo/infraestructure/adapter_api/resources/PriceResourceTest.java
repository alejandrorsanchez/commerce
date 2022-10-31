package com.example.demo.infraestructure.adapter_api.resources;

import com.example.demo.infraestructure.adapter_api.dtos.PriceDTO;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:test.properties")
public class PriceResourceTest {

    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 35455L;
    private final LocalDateTime APPLICATION_DATE1 = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private final LocalDateTime APPLICATION_DATE2 = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
    private final LocalDateTime APPLICATION_DATE3 = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
    private final LocalDateTime APPLICATION_DATE4 = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
    private final LocalDateTime APPLICATION_DATE5 = LocalDateTime.of(2020, 6, 16, 21, 0, 0);

    @Autowired
    H2dbSeeder h2dbSeeder;
    @Autowired
    PriceResource priceResource;

    @BeforeEach
    void populate(){ h2dbSeeder.populateDB(); }

    @AfterEach
    void tearDown(){ h2dbSeeder.tearDown(); }

    @Test
    void findCurrentPriceDay14Hour10Test(){
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE1, BigDecimal.valueOf(35.50));
    }

    @Test
    void findCurrentPriceDay14Hour16Test(){
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE2, BigDecimal.valueOf(25.45));
    }

    @Test
    void findCurrentPriceDay14Hour21Test(){
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE3, BigDecimal.valueOf(35.50));
    }

    @Test
    void findCurrentPriceDay15Hour10Test(){
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE4, BigDecimal.valueOf(30.50));
    }

    @Test
    void findCurrentPriceDay16Hour21Test(){
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE5, BigDecimal.valueOf(38.95));
    }

    private void verifyCorrectPriceForApplicationDate(LocalDateTime applicationDate, BigDecimal expectedPrice) {
        PriceDTO priceDTO = priceResource.findCurrentPrice(BRAND_ID, PRODUCT_ID, applicationDate);
        assertTrue(priceDTO.getPriceValue().compareTo(expectedPrice) == 0);
    }

}
