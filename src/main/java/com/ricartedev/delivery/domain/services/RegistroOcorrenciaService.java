package com.ricartedev.delivery.domain.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ricartedev.delivery.domain.models.Entrega;
import com.ricartedev.delivery.domain.models.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

  private BuscaEntregaService buscaEntregaService;

  @Transactional
  public Ocorrencia registrar( Long entregaId, String descricao) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);

    return entrega.adicionarOcorrencia(descricao);

  }
  
}
