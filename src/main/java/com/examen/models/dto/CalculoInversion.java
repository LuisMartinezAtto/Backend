package com.examen.models.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CalculoInversion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer ano;
	private Double saldoInicial;
	private Double aportacion;
	private Double rendimiento;
	private Double saldoFinal;
	

}
