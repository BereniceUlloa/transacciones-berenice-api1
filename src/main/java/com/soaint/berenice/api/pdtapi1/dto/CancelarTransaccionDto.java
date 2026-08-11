package com.soaint.berenice.api.pdtapi1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelarTransaccionDto {
	private Long id;
	private String referencia;
	private String estatus; // Recibe la leyenda "cancelar"
}