package com.examen.models.service;

import java.util.List;
import java.util.Map;

import com.examen.enums.Validacion;
import com.examen.models.dto.CalculoInversion;
import com.examen.models.entity.Inversion;

public interface IInversionService {
	
	public List<Inversion> findAll();
	
	public Inversion findById(Long id);
	
	public Inversion save(Inversion inversion);
	
	public Validacion validacion (Inversion inversion);
	
	public Map<String ,Object> calcularInversion(Inversion inversion);
	
	
}
