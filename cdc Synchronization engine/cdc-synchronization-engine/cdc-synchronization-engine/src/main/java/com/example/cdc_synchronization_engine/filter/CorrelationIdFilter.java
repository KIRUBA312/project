package com.example.cdc_synchronization_engine.filter;


import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.cdc_synchronization_engine.util.CorrelationIdUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CorrelationIdFilter 
        extends OncePerRequestFilter {


    private static final String HEADER =
            "X-Correlation-ID";


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {


        String correlationId =
                request.getHeader(HEADER);



        if(correlationId == null ||
                correlationId.isBlank()){


            correlationId =
                    java.util.UUID.randomUUID()
                    .toString();
        }


        org.slf4j.MDC.put(
                "correlationId",
                correlationId
        );


        response.setHeader(
                HEADER,
                correlationId
        );


        try{

            filterChain.doFilter(
                    request,
                    response
            );

        }
        finally{

            CorrelationIdUtil.clear();
        }

    }

}