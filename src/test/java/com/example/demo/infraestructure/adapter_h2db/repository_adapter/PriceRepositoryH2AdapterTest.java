package com.example.demo.infraestructure.adapter_h2db.repository_adapter;

import com.example.demo.domain.models.Price;
import com.example.demo.domain.models.PriceSearchGroup;
import com.example.demo.infraestructure.adapter_h2db.daos.PriceDao;
import com.example.demo.infraestructure.adapter_h2db.entities.PriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:test.properties")
class PriceRepositoryH2AdapterTest {

    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 35455L;
    private static final String EUR = "EUR";

    private PriceDao priceDao;
    private PriceRepositoryH2Adapter priceRepositoryH2Adapter;

    private PriceSearchGroup buildPriceSearchGroup(Long brandId, Long productId, LocalDateTime applicationDate){
        return PriceSearchGroup.builder()
                                .brandId(brandId)
                                .productId(productId)
                                .applicationDate(applicationDate)
                                .build();
    }

    private void mockPriceRepositoryH2Adapter(List<PriceEntity> prices){
        priceDao = mock(PriceDao.class);
        when(priceDao.findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(anyLong(), anyLong(), any(), any()))
                .thenReturn(prices);
        priceRepositoryH2Adapter = new PriceRepositoryH2Adapter(priceDao);
    }

    @Test
    void findCurrentPriceDay14Hour10Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        PriceEntity priceEntity = PriceEntity.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .priority(0)
                .priceValue(BigDecimal.valueOf(35.50))
                .currency(EUR)
                .build();
        mockPriceRepositoryH2Adapter(List.of(priceEntity));
        List<Price> prices = priceRepositoryH2Adapter.findCurrentPrice(buildPriceSearchGroup(BRAND_ID, PRODUCT_ID, APPLICATION_DATE));
        assertEquals(1, prices.size());
        assertEquals(0, (prices.stream()
                                        .findFirst()
                                        .get()
                                        .getPriceValue()
                                ).compareTo(BigDecimal.valueOf(35.50)));
    }

    @Test
    void findCurrentPriceDay14Hour16Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        mockPriceRepositoryH2Adapter(List.of(PriceEntity.builder().build(), PriceEntity.builder().build()));
        List<Price> prices = priceRepositoryH2Adapter.findCurrentPrice(buildPriceSearchGroup(BRAND_ID, PRODUCT_ID, APPLICATION_DATE));
        assertEquals(2, prices.size());
    }

    @Test
    void findCurrentPriceDay14Hour21Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        PriceEntity priceEntity = PriceEntity.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1)
                .priority(0)
                .priceValue(BigDecimal.valueOf(35.50))
                .currency(EUR)
                .build();
        mockPriceRepositoryH2Adapter(List.of(priceEntity));
        List<Price> prices = priceRepositoryH2Adapter.findCurrentPrice(buildPriceSearchGroup(BRAND_ID, PRODUCT_ID, APPLICATION_DATE));
        assertEquals(1, prices.size());
        assertEquals(0, (prices.stream()
                                        .findFirst()
                                        .get()
                                        .getPriceValue()
                                ).compareTo(BigDecimal.valueOf(35.50)));
    }

    @Test
    void findCurrentPriceDay15Hour10Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        mockPriceRepositoryH2Adapter(List.of(PriceEntity.builder().build(), PriceEntity.builder().build()));
        List<Price> prices = priceRepositoryH2Adapter.findCurrentPrice(buildPriceSearchGroup(BRAND_ID, PRODUCT_ID, APPLICATION_DATE));
        assertEquals(2, prices.size());
    }

    @Test
    void findCurrentPriceDay16Hour21Test(){
        final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        mockPriceRepositoryH2Adapter(List.of(PriceEntity.builder().build(), PriceEntity.builder().build()));
        List<Price> prices = priceRepositoryH2Adapter.findCurrentPrice(buildPriceSearchGroup(BRAND_ID, PRODUCT_ID, APPLICATION_DATE));
        assertEquals(2, prices.size());
    }
}
