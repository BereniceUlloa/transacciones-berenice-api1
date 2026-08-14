package com.soaint.berenice.api.pdtapi1.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
    	System.out.println("entro en el interceptor para reeviar token");
        // Obtener la petición HTTP actual del contexto de Spring Web
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader(AUTHORIZATION_HEADER);

            // Si la petición entrante trae el token "Bearer ...", reenviarlo a Feign
            if (token != null && !token.isEmpty()) {
                template.header(AUTHORIZATION_HEADER, token);
            }
        }
    }
}