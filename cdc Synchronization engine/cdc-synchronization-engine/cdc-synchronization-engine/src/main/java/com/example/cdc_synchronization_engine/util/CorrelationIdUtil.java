package com.example.cdc_synchronization_engine.util;


import org.slf4j.MDC;

import java.util.UUID;


public class CorrelationIdUtil {


    public static final String CORRELATION_ID =
            "correlationId";


    public static String getCorrelationId(){

        String id = MDC.get(CORRELATION_ID);


        if(id == null){

            id = UUID.randomUUID()
                    .toString();

            MDC.put(
                    CORRELATION_ID,
                    id
            );
        }


        return id;
    }


    public static void clear(){

        MDC.remove(
                CORRELATION_ID
        );
    }

}