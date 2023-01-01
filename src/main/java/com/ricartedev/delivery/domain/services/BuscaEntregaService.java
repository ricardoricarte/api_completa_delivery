package com.ricartedev.delivery.domain.services;

import org.springframework.stereotype.Service;

import com.ricartedev.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.ricartedev.delivery.domain.models.Entrega;
import com.ricartedev.delivery.domain.repositories.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

  public EntregaRepository entregaRepository;

  public Entrega buscar(Long entregaId) {
   return entregaRepository.findById(entregaId)
    .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
  }
  
}
