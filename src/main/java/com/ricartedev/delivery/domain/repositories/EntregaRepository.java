package com.ricartedev.delivery.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricartedev.delivery.domain.models.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
  
}
