package com.ricartedev.delivery.api.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ricartedev.delivery.api.assembler.OcorrenciaAssembler;
import com.ricartedev.delivery.api.modelDTO.OcorrenciaModelDTO;
import com.ricartedev.delivery.api.modelDTO.input.OcorrenciaInput;
import com.ricartedev.delivery.domain.models.Entrega;
import com.ricartedev.delivery.domain.models.Ocorrencia;
import com.ricartedev.delivery.domain.services.BuscaEntregaService;
import com.ricartedev.delivery.domain.services.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

  private RegistroOcorrenciaService registroOcorrenciaService;
  private OcorrenciaAssembler ocorrenciaAssembler;
  private BuscaEntregaService buscaEntregaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OcorrenciaModelDTO registrar(@PathVariable Long entregaId,
    @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
      Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
      .registrar(entregaId, ocorrenciaInput.getDescricao());
      return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);

  }

  @GetMapping
  public List<OcorrenciaModelDTO> listar(@PathVariable Long entregaId) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);
    return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());

  }
  
}
