package com.example.demo.infraestructure.adapter_h2db.daos;

import com.example.demo.infraestructure.adapter_h2db.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceDao extends JpaRepository<PriceEntity, Long> {
    
    List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanAndEndDateGreaterThan(Long brandId,
                                                                                         Long productId,
                                                                                         LocalDateTime applicationDate1,
                                                                                         LocalDateTime applicationDate2);
}
