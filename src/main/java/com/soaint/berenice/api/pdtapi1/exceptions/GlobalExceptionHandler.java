package com.soaint.berenice.api.pdtapi1.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errores = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errores.put(error.getField(), error.getDefaultMessage());
		}

		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("status", HttpStatus.BAD_REQUEST.value());
		respuesta.put("error", "Bad Request");
		respuesta.put("detalles", errores);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("timestamp", LocalDateTime.now(), "status",
				401, "error", "Unauthorized", "message", "Token inválido, expirado o no proporcionado."));
	}

}