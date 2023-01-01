package com.ricartedev.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ricartedev.delivery.api.assembler.EntregaAssembler;
import com.ricartedev.delivery.api.modelDTO.EntregaModelDTO;
import com.ricartedev.delivery.api.modelDTO.input.EntregaInput;
import com.ricartedev.delivery.domain.models.Entrega;
import com.ricartedev.delivery.domain.repositories.EntregaRepository;
import com.ricartedev.delivery.domain.services.FinalizacaoEntregaService;
import com.ricartedev.delivery.domain.services.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

  private EntregaAssembler entregaAssembler;
  private EntregaRepository entregaRepository;
  private SolicitacaoEntregaService solicitacaoEntregaService;
  private FinalizacaoEntregaService finalizacaoEntregaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntregaModelDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
    Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
    Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
    
    return entregaAssembler.toModel(entregaSolicitada);
  }

  @GetMapping
  public List<EntregaModelDTO> listar() {
    return entregaAssembler.toCollectionModel(entregaRepository.findAll());
  }

  @GetMapping("/{entregaId}")
  public ResponseEntity<EntregaModelDTO> buscarEntregaPorId(@PathVariable Long entregaId) {
    return entregaRepository.findById(entregaId)
       .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
       .orElse(ResponseEntity.notFound().build());     
  }

  @PutMapping("/{entregaId}/finalizacao")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void finalizar(@PathVariable Long entregaId) {
    finalizacaoEntregaService.finalizar(entregaId);
  }
}
