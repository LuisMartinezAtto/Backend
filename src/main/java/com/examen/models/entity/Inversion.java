package com.examen.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "inversiones")
public class Inversion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long    id;
	@Column(name = "inversion_inicial")
	private Double	inversionInicial;
	@Column(name = "aportacion_anual")
	private Double  aportacionAnual;
	@Column(name = "incremento_aportacion_anual")
	private Double  incrementoAportacionAnual;
	@Column(name = "anos_inversion")
	private Double  anosInversion;
	private Double  rendimiento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getInversionInicial() {
		return inversionInicial;
	}
	public void setInversionInicial(Double inversionInicial) {
		this.inversionInicial = inversionInicial;
	}
	public Double getAportacionAnual() {
		return aportacionAnual;
	}
	public void setAportacionAnual(Double aportacionAnual) {
		this.aportacionAnual = aportacionAnual;
	}
	public Double getIncrementoAportacionAnual() {
		return incrementoAportacionAnual;
	}
	public void setIncrementoAportacionAnual(Double incrementoAportacionAnual) {
		this.incrementoAportacionAnual = incrementoAportacionAnual;
	}
	public Double getAnosInversion() {
		return anosInversion;
	}
	public void setAnosInversion(Double anosInversion) {
		this.anosInversion = anosInversion;
	}
	public Double getRendimiento() {
		return rendimiento;
	}
	public void setRendimiento(Double rendimiento) {
		this.rendimiento = rendimiento;
	}
	
	

}
