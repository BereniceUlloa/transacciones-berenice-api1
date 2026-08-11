package com.soaint.berenice.api.pdtapi1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TransaccionRequestDto {

	// Validar que solo contenga caracteres alfabéticos (a-z, A-Z)
	@NotBlank(message = "La operación es obligatoria")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El campo operación solo debe contener caracteres")
	private String operacion;

	// Validar formato de moneda (ejemplo: 100, 100.00, 50.5)
	@NotBlank(message = "El importe es obligatorio")
	@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "El importe debe tener un formato de moneda válido (ej. 100.00)")
	private String importe;

	// Validar que solo contenga caracteres alfabéticos
	@NotBlank(message = "El cliente es obligatorio")
	@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El campo cliente solo debe contener caracteres")
	private String cliente;

	// Clave cifrada recibida en AES-256 desde el frontend
	@NotBlank(message = "El secreto es obligatorio")
	private String secreto;
}
