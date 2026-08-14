package com.soaint.berenice.api.pdtapi1.client;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

@RestControllerAdvice
public class FeignErrorAdvice {

	@ExceptionHandler(FeignException.Unauthorized.class)
	public ResponseEntity<Map<String, Object>> handleFeignUnauthorizedException(FeignException.Unauthorized ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(Map.of("timestamp", LocalDateTime.now(), "status", 401, "error", "Unauthorized", "message",
						"El servicio downstream (Micro 2) rechazó la autenticación del token."));
	}
}