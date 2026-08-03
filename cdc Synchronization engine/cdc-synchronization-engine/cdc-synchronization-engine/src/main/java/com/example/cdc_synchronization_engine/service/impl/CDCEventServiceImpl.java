package com.example.cdc_synchronization_engine.service.impl;


import org.springframework.stereotype.Service;


import com.example.cdc_synchronization_engine.kafka.model.CDCEvent;
import com.example.cdc_synchronization_engine.kafka.producer.CDCEventProducer;
import com.example.cdc_synchronization_engine.service.CDCEventService;


import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class CDCEventServiceImpl 
        implements CDCEventService {


    private final CDCEventProducer producer;



    @Override
    public void publishEvent(
            String topic,
            CDCEvent event){


    	 producer.publishEvent(
                 topic,
                 event.getEntityName(),
                 event.getEntityId(),
                 event.getOperation(),
                 event.getPayload()
         );

    }


}