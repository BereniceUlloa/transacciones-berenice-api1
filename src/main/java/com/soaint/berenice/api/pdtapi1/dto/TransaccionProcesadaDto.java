package com.soaint.berenice.api.pdtapi1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionProcesadaDto {

	// Es la Clave Primaria (PK) del registro almacenado en la base de datos H2
	private String id;

	// Estado inicial generado ("Aprobada")
	private String estatus;

	// Referencia numérica aleatoria de 6 dígitos
	private String referencia;

	// Tipo de operación procesada (ej. "venta")
	private String operacion;
}