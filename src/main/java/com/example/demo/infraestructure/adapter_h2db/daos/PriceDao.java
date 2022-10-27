package com.example.demo.infraestructure.adapter_h2db.daos;

import com.example.demo.infraestructure.adapter_h2db.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceDao extends JpaRepository<PriceEntity, Long> {



}
