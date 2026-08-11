package com.soaint.berenice.api.pdtapi1.service;

public interface AesDecryptService {
	/**
     * Descifra una cadena cifrada en AES-256 provista en formato Base64.
     *
     * @param textoCifrado Cadena cifrada enviada desde el frontend.
     * @return Cadena de texto plano descifrada.
     */
    String descifrar(String textoCifrado);
}
