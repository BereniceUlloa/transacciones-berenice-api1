package com.soaint.berenice.api.pdtapi1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionResponseDto {
    private Long id;
    private String operacion;
    private String importe;
    private String cliente;
    private String referencia;
    private String estatus;
    private String secreto;
}