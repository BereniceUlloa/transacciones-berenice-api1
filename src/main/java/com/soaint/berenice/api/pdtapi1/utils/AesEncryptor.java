package com.soaint.berenice.api.pdtapi1.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AesEncryptor {

    public static String cifrar(String textoPlano, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        
        byte[] bytesEncrypted = cipher.doFinal(textoPlano.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytesEncrypted);
    }

    public static void main(String[] args) throws Exception {
        String clave = "12345678901234567890123456789012";
        String texto = "secreto123"; // El texto plano que quieres cifrar
        
        System.out.println("Texto cifrado para Postman: " + cifrar(texto, clave));
    }
}