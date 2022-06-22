package com.nineteam.marketkurlycloneproject.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nineteam.marketkurlycloneproject.security.dto.ResponseDto;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException | IllegalArgumentException e) {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            ResponseDto responseDto = new ResponseDto(false, e.getMessage());
            objectMapper.writeValue(response.getWriter(), responseDto);
        }

    }
}
