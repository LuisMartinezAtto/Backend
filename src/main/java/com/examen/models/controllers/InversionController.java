package com.examen.models.controllers;

import com.examen.enums.Validacion;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examen.exceptions.GeneralException;
import com.examen.models.entity.Inversion;
import com.examen.models.service.IInversionService;

@RestController
public class InversionController {
	
	@Autowired
	private IInversionService inversionService;

	@CrossOrigin
	@GetMapping("listar")
	public List<Inversion> listar(){
		return inversionService.findAll();
	}

	@CrossOrigin
	@GetMapping("listar/{id}")
	public Inversion detalle(@PathVariable Long id){
		return inversionService.findById(id);
	}

	@CrossOrigin
	@PostMapping(path = "save",
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Inversion> save(@RequestBody Inversion pInversion){
		var inversion = inversionService.save(pInversion);
		if (inversion == null) {
			throw new GeneralException("No se guardardo la información");
		}else {
			return new ResponseEntity<Inversion>(inversion,HttpStatus.CREATED);
		}		
	}

	@CrossOrigin
	@PostMapping(path = "valida",
	        consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> valida(@RequestBody Inversion pInversion){
		 var valida = inversionService.validacion(pInversion);
		 return new ResponseEntity<>(valida.getMensaje(),HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping(path = "calculaInversion",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map> calculaInversion(@RequestBody Inversion pInversion){
		var valida = inversionService.validacion(pInversion);
		Map salida = new HashMap();
		if (valida == Validacion.ERROR){
			salida.put("error",valida.getMensaje());
			return new ResponseEntity<>(salida,HttpStatus.OK);
		}
		return new ResponseEntity<>(inversionService.calcularInversion(pInversion),HttpStatus.OK);

	}

}
