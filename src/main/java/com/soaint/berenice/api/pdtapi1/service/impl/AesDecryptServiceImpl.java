package com.soaint.berenice.api.pdtapi1.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soaint.berenice.api.pdtapi1.service.AesDecryptService;

@Service
public class AesDecryptServiceImpl implements AesDecryptService {
	

	    @Value("${app.security.aes-secret-key:12345678901234567890123456789012}")
	    private String secretKey;

	    public String descifrar(String textoCifradoBase64) {
	        try {
	            // 1. Limpiar espacios accidentales producidos en la transmisión del JSON
	            String limpio = textoCifradoBase64.trim().replace(" ", "+");

	            // 2. DECODIFICAR DE BASE64 A BYTES (Paso crítico que evita el IllegalBlockSizeException)
	            byte[] bytesEncrypted = Base64.getDecoder().decode(limpio);

	            // 3. Crear la clave para AES-256 (debe tener 32 bytes/caracteres)
	            SecretKeySpec keySpec = new SecretKeySpec(
	                secretKey.getBytes(StandardCharsets.UTF_8), "AES"
	            );

	            // 4. Configurar el Cipher en modo desencriptación
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	            cipher.init(Cipher.DECRYPT_MODE, keySpec);

	            // 5. Descifrar los bytes correctamente alineados
	            byte[] bytesDecrypted = cipher.doFinal(bytesEncrypted);

	            return new String(bytesDecrypted, StandardCharsets.UTF_8);
	        } catch (Exception e) {
	            throw new RuntimeException("Error al descifrar el texto con el algoritmo AES-256: " + e.getMessage(), e);
	        }
	    }
	}