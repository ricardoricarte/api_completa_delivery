package com.ricartedev.delivery.api.modelDTO;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaModelDTO {

  private Long id;
  private String descricao;
  private OffsetDateTime dataRegistro;
}
