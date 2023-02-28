package com.examen.enums;

public enum Validacion {
	OK("Datos Correctos"),
	ERROR ("No es posible procesar su solicitud con los datos proporcionados");
	private String mensaje;
	    Validacion(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	

}
