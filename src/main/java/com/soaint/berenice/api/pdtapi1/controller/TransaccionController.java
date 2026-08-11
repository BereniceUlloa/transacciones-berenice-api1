package com.soaint.berenice.api.pdtapi1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soaint.berenice.api.pdtapi1.client.PdtApi2FeignClient;
import com.soaint.berenice.api.pdtapi1.dto.CancelarTransaccionDto;
import com.soaint.berenice.api.pdtapi1.dto.PageResponseDto;
import com.soaint.berenice.api.pdtapi1.dto.TransaccionProcesadaDto;
import com.soaint.berenice.api.pdtapi1.dto.TransaccionRequestDto;
import com.soaint.berenice.api.pdtapi1.dto.TransaccionResponseDto;
import com.soaint.berenice.api.pdtapi1.service.AesDecryptService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/transacciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Ajustar según la URL del frontend Angular
public class TransaccionController {

	private final AesDecryptService aesDecryptService;
	private final PdtApi2FeignClient api2FeignClient;

	@PostMapping("/procesar")
	public ResponseEntity<TransaccionProcesadaDto> procesarTransaccion(
			@Valid @RequestBody TransaccionRequestDto requestDto) {

		// 1. Descifrar el atributo 'secreto' recibido en AES-256 desde el frontend
		String secretoDescifrado = aesDecryptService.descifrar(requestDto.getSecreto());
		requestDto.setSecreto(secretoDescifrado);

		// 2. Reenviar la petición validada y descifrada a la API 2 mediante OpenFeign
		TransaccionProcesadaDto responseApi2 = api2FeignClient.guardarTransaccion(requestDto);

		// 3. Responder al frontend con el resultado obtenido de la API 2
		return ResponseEntity.ok(responseApi2);
	}

	// PATCH /api/v1/transacciones/cancelar
	@PatchMapping("/cancelar")
	public ResponseEntity<String> cancelar(@RequestBody CancelarTransaccionDto dto) {
		return api2FeignClient.cancelarTransaccion(dto);
	}

	// GET /api/v1/transacciones
	@GetMapping
	public ResponseEntity<PageResponseDto<TransaccionResponseDto>> listar(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "ASC") String direction) {

		PageResponseDto<TransaccionResponseDto> respuesta = api2FeignClient.listarTransacciones(page, size, sortBy,
				direction);
		return ResponseEntity.ok(respuesta);
	}
}