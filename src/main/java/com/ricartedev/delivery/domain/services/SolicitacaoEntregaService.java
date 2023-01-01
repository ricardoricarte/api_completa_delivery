package com.ricartedev.delivery.domain.services;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ricartedev.delivery.domain.models.Cliente;
import com.ricartedev.delivery.domain.models.Entrega;
import com.ricartedev.delivery.domain.models.StatusEntrega;
import com.ricartedev.delivery.domain.repositories.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

  private CatalagoClienteService catalogClienteService;
  private EntregaRepository entregaRepository;

  @Transactional
  public Entrega solicitar(Entrega entrega) {
    Cliente cliente = catalogClienteService.buscar(entrega.getCliente().getId());

    entrega.setCliente(cliente);
    entrega.setStatus(StatusEntrega.PENDENTE);
    entrega.setDataPedido(OffsetDateTime.now());
    
    return entregaRepository.save(entrega);
  }
  
}
