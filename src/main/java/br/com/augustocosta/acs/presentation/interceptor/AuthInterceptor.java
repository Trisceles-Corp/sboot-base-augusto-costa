package br.com.augustocosta.acs.presentation.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        boolean isAuthenticated = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName()) && isValidSession(cookie.getValue())) {
                    isAuthenticated = true;
                    break;
                }
            }
        }

        if (!isAuthenticated) {
            response.sendRedirect("/acesso");
            return false;
        }

        return true;
    }

    private boolean isValidSession(String sessionId) {
        // Implemente a lógica para verificar se o sessionId é válido
        return true; // Exemplo: retorna true se válido
    }
}