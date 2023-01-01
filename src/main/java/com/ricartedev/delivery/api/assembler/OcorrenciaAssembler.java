package com.ricartedev.delivery.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ricartedev.delivery.api.modelDTO.OcorrenciaModelDTO;
import com.ricartedev.delivery.domain.models.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

  private ModelMapper modelMapper;

  public OcorrenciaModelDTO toModel(Ocorrencia ocorrencia) {
    return modelMapper.map(ocorrencia, OcorrenciaModelDTO.class);
  }

  public List<OcorrenciaModelDTO> toCollectionModel(List<Ocorrencia> ocorrencias) {
    return ocorrencias
       .stream()
       .map(this::toModel)
       .collect(Collectors.toList());

  }
  
}
