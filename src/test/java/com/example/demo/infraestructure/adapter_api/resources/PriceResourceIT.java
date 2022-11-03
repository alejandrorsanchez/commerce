package com.example.demo.infraestructure.adapter_api.resources;

import com.example.demo.infraestructure.adapter_api.dtos.PriceDTO;
import com.example.demo.infraestructure.adapter_h2db.seeder.H2dbSeeder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestPropertySource(
        locations = "classpath:test.properties")
class PriceResourceIT {

    public static final long BRAND_ID = 1L;
    public static final long PRODUCT_ID = 35455L;
    public static final long NOT_FOUND_BRAND_ID = 0;
    public static final long NOT_FOUND_PRODUCT_ID = 0;
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    H2dbSeeder h2dbSeeder;

    @BeforeEach
    void setUp() {
        h2dbSeeder.populateDB();
    }

    @AfterEach
    void tearDown() {
        h2dbSeeder.tearDown();
    }

    @Test
    void findCurrentPriceOKTest(){
        PriceDTO expectedPriceDTO = PriceDTO.builder()
                                            .brandId(BRAND_ID)
                                            .productId(PRODUCT_ID)
                                            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                                            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                                            .priceValue(BigDecimal.valueOf(35.50))
                                            .build();
        this.webTestClient
            .get()
            .uri(uriBuilder -> uriBuilder.path(PriceResource.PRICES + PriceResource.SEARCH)
                    .queryParam("brandId", expectedPriceDTO.getBrandId())
                    .queryParam("productId", expectedPriceDTO.getProductId())
                    .queryParam("applicationDate", "2020-06-14T21:00:00.000Z")
                    .build())
            .exchange()
            .expectStatus().isOk()
            .expectBody(PriceDTO.class)
            .value(Assertions::assertNotNull)
            .value(priceDTO ->
                assertEquals(expectedPriceDTO, priceDTO)
            );
    }

    @Test
    void findCurrentPriceNotFoundTest(){
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(PriceResource.PRICES + PriceResource.SEARCH)
                        .queryParam("brandId", NOT_FOUND_BRAND_ID)
                        .queryParam("productId", NOT_FOUND_PRODUCT_ID)
                        .queryParam("applicationDate", "2018-06-14T21:00:00.000Z")
                        .build())
                .exchange()
                .expectStatus().isNotFound();
    }

}
