package com.example.multiregion_resilience.mapper;

import org.springframework.stereotype.Component;

import com.example.multiregion_resilience.dto.FailoverResponse;
import com.example.multiregion_resilience.entity.FailoverEvent;

@Component
public class FailoverEventMapper {

	public FailoverResponse toResponse(
            FailoverEvent event
    ) {

        if (event == null) {
            return null;
        }

        FailoverResponse response = new FailoverResponse();

        response.setId(
                event.getId()
        );

        response.setSourceRegion(
                event.getSourceRegion()
        );

        response.setTargetRegion(
                event.getTargetRegion()
        );

        response.setFailoverType(
                event.getFailoverType()
        );

        response.setReason(
                event.getReason()
        );

        response.setTriggeredBy(
                event.getTriggeredBy()
        );

        response.setCreatedAt(
                event.getCreatedAt()
        );

        return response;
    }
}
