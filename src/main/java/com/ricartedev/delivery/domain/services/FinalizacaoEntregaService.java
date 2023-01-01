package com.ricartedev.delivery.domain.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ricartedev.delivery.domain.models.Entrega;
import com.ricartedev.delivery.domain.repositories.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

  private EntregaRepository entregaRepository;
  private BuscaEntregaService buscaEntregaService;

  @Transactional
  public void finalizar(Long entregaId) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);
      entrega.finalizar();

      entregaRepository.save(entrega);
    }
  }
  
