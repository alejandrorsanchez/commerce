package com.example.demo.infraestructure.adapter_api.resources;

import com.example.demo.application.use_cases.FindCurrentPriceUseCase;
import com.example.demo.domain.models.Price;
import com.example.demo.domain.models.PriceSearchGroup;
import com.example.demo.infraestructure.adapter_api.dtos.PriceDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:test.properties")
class PriceResourceTest {

    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 35455L;
    private static final String EUR = "EUR";

    private PriceResource priceResource;
    private FindCurrentPriceUseCase findCurrentPriceUseCase;

    private PriceSearchGroup buildPriceSearchGroup(Long brandId, Long productId, LocalDateTime applicationDate){
        return PriceSearchGroup.builder()
                .brandId(brandId)
                .productId(productId)
                .applicationDate(applicationDate)
                .build();
    }

    private void mockPriceResource(Price price){
        findCurrentPriceUseCase = mock(FindCurrentPriceUseCase.class);
        when(findCurrentPriceUseCase.findCurrentPrice(buildPriceSearchGroup(BRAND_ID, PRODUCT_ID, any())))
                .thenReturn(price);
        priceResource = new PriceResource(findCurrentPriceUseCase);
    }

    @Test
    void findCurrentPriceDay14Hour10Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Price expectedPrice = Price.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .priority(0)
                .priceValue(BigDecimal.valueOf(35.50))
                .currency(EUR)
                .build();
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE, expectedPrice);
    }

    @Test
    void findCurrentPriceDay14Hour16Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        Price expectedPrice = Price.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
                .priceList(2)
                .priority(1)
                .priceValue(BigDecimal.valueOf(25.45))
                .currency(EUR)
                .build();
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE, expectedPrice);
    }

    @Test
    void findCurrentPriceDay14Hour21Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Price expectedPrice = Price.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .priority(0)
                .priceValue(BigDecimal.valueOf(35.50))
                .currency(EUR)
                .build();
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE, expectedPrice);
    }

    @Test
    void findCurrentPriceDay15Hour10Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Price expectedPrice = Price.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                .priceList(3)
                .priority(1)
                .priceValue(BigDecimal.valueOf(30.50))
                .currency(EUR)
                .build();
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE, expectedPrice);
    }

    @Test
    void findCurrentPriceDay16Hour21Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Price expectedPrice = Price.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(4)
                .priority(1)
                .priceValue(BigDecimal.valueOf(38.95))
                .currency(EUR)
                .build();
        verifyCorrectPriceForApplicationDate(APPLICATION_DATE, expectedPrice);
    }

    private void verifyCorrectPriceForApplicationDate(LocalDateTime applicationDate, Price expectedPrice) {
        mockPriceResource(expectedPrice);
        PriceDTO priceDTO = priceResource.findCurrentPrice(BRAND_ID, PRODUCT_ID, applicationDate);
        assertEquals(0, priceDTO.getPriceValue().compareTo(expectedPrice.getPriceValue()));
    }

}
