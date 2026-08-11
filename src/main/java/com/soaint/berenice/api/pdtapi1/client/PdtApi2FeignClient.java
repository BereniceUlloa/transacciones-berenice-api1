package com.soaint.berenice.api.pdtapi1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.soaint.berenice.api.pdtapi1.dto.CancelarTransaccionDto;
import com.soaint.berenice.api.pdtapi1.dto.PageResponseDto;
import com.soaint.berenice.api.pdtapi1.dto.TransaccionProcesadaDto;
import com.soaint.berenice.api.pdtapi1.dto.TransaccionRequestDto;
import com.soaint.berenice.api.pdtapi1.dto.TransaccionResponseDto;

@FeignClient(name = "api2-service", url = "${api2.url:http://localhost:8082}")
public interface PdtApi2FeignClient {

    // 1. Guardar Transacción
    @PostMapping("/api/v1/transacciones/guardar")
    TransaccionProcesadaDto guardarTransaccion(@RequestBody TransaccionRequestDto request);

    // 2. Cancelar Transacción (Método PATCH) - Se agrega la ruta completa
    @PatchMapping("/api/v1/transacciones/cancelar")
    ResponseEntity<String> cancelarTransaccion(@RequestBody CancelarTransaccionDto dto);

    // 3. Consulta Paginada (Método GET) - Se agrega la ruta completa
    @GetMapping("/api/v1/transacciones")
    PageResponseDto<TransaccionResponseDto> listarTransacciones(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("direction") String direction
    );
}