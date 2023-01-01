package com.ricartedev.delivery.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ricartedev.delivery.api.modelDTO.EntregaModelDTO;
import com.ricartedev.delivery.api.modelDTO.input.EntregaInput;
import com.ricartedev.delivery.domain.models.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {

  private ModelMapper modelMapper;

  public EntregaModelDTO toModel(Entrega entrega) {
    return modelMapper.map(entrega, EntregaModelDTO.class);
  }

  public List<EntregaModelDTO> toCollectionModel(List<Entrega> entregas) {
    return entregas.stream()
       .map(this::toModel)
       .collect(Collectors.toList());
  }

  public Entrega toEntity(EntregaInput entregaInput) {
    return modelMapper.map(entregaInput, Entrega.class);
  }

  
}
