package com.ricartedev.delivery.api.modelDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ricartedev.delivery.domain.models.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModelDTO {

  private Long id;
  private ClienteResumoModel cliente;;
  private DestinatarioModelDTO destinatario;
  private BigDecimal taxa;
  private StatusEntrega status;
  private OffsetDateTime dataPedido;
  private OffsetDateTime dataFinalizacao;

  
}
