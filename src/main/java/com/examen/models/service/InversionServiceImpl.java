package com.examen.models.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.enums.Validacion;
import com.examen.models.dao.InversionDao;
import com.examen.models.dto.CalculoInversion;
import com.examen.models.entity.Inversion;

@Service
public class InversionServiceImpl implements IInversionService {
	
	@Autowired
	InversionDao inversionDao; 

	@Override
	@Transactional(readOnly = true)
	public List<Inversion> findAll() {
		return (List<Inversion>) inversionDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Inversion findById(Long id) {
		return inversionDao.findById(id).orElse(null);
	}
	
	@Transactional()
    public Inversion save(Inversion inversion) {
		if (inversion.getAportacionAnual() == null || inversion.getAportacionAnual() < 0)
			inversion.setAportacionAnual(0.0);
		if (inversion.getIncrementoAportacionAnual() == null )
			inversion.setIncrementoAportacionAnual(0.0);
    	return inversionDao.save(inversion);
	}
	
	public Validacion validacion (Inversion inversion) {
		if (inversion.getInversionInicial() == null ||inversion.getInversionInicial() < 1000)
			return Validacion.ERROR;
		if (inversion.getAnosInversion() == null || inversion.getAnosInversion() < 0)
			return Validacion.ERROR;
		if (inversion.getRendimiento() == null ||inversion.getRendimiento() < 0)
			return Validacion.ERROR;
		return Validacion.OK;
	}
	
	public Map<String ,Object> calcularInversion(Inversion inversion){
		HashMap<String , Object> salida = new HashMap<String, Object>();
	    List <CalculoInversion> listaCalculoInversion = new ArrayList<CalculoInversion>();
	    if (validacion(inversion) == Validacion.OK) {
	    	inversion = save(inversion);
			Double saldoIncial = inversion.getInversionInicial();
			Double aportacion = inversion.getAportacionAnual();
			Double saldoFinal =0.0;
			Double sumaAportacion =0.0;
			for (int i = 1; i <= inversion.getAnosInversion(); i++) {
				if (i>1){
					saldoIncial =saldoFinal;
					aportacion =  Math.ceil(aportacion * (1.0 + (inversion.getIncrementoAportacionAnual() /100.0 )) );
				}
			    Double rendimiento =Math.ceil( (saldoIncial+aportacion) * (inversion.getRendimiento()/100.0));
				saldoFinal = saldoIncial+aportacion+rendimiento;
				listaCalculoInversion.add(CalculoInversion
						.builder()
						.ano(i)
						.saldoInicial(saldoIncial)
						.aportacion(aportacion)
						.rendimiento(rendimiento)
						.saldoFinal(saldoFinal)
						.build());
				sumaAportacion+=aportacion;
			}
			salida.put("MontoFinal",saldoFinal);
			salida.put("GananciaInversion",saldoFinal - inversion.getInversionInicial()-sumaAportacion);
			salida.put("CalculoInversion",listaCalculoInversion);
	    }
		return salida;
		
	}

}
