package com.example.multiregion_resilience.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.multiregion_resilience.dto.FailoverRequest;
import com.example.multiregion_resilience.dto.FailoverResponse;
import com.example.multiregion_resilience.dto.PageResponse;
import com.example.multiregion_resilience.service.FailoverService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/failover")
public class FailoverController {

	private final FailoverService failoverService;
	private final String idempotencyHeaderName;

	public FailoverController(FailoverService failoverService, 
			@Value("${idempotency.header-name:Idempotency-Key}")
			String idempotencyHeaderName) {
		super();
		this.failoverService = failoverService;
		this.idempotencyHeaderName = idempotencyHeaderName;
	}
	
	 @PostMapping
	    public ResponseEntity<FailoverResponse>
	    performFailover(
	            @RequestHeader(
	                    name = "${idempotency.header-name:Idempotency-Key}",
	                    required = true
	            )
	            String idempotencyKey,

	            @Valid
	            @RequestBody
	            FailoverRequest request) {

	        FailoverResponse response =
	                failoverService.performFailover(
	                        request,
	                        idempotencyKey
	                );

	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(response);
	    }

	    @PostMapping("/failback")
	    public ResponseEntity<FailoverResponse>
	    performFailback(
	            @RequestHeader(
	                    name = "${idempotency.header-name:Idempotency-Key}",
	                    required = true
	            )
	            String idempotencyKey,

	            @Valid
	            @RequestBody
	            FailoverRequest request) {
	        FailoverResponse response =
	        		failoverService.performFailback(
	                        request,
	                        idempotencyKey
	                );

	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(response);
	    }
	@GetMapping("/{id}")
	public ResponseEntity<FailoverResponse> getFailoverById(
			@PathVariable Long id){
		FailoverResponse response = failoverService
				.getFailoverById(id);
		return ResponseEntity.ok(response);
	}
    @GetMapping
    public ResponseEntity<PageResponse<FailoverResponse>>
    getAllFailovers(

            @RequestParam(
                    defaultValue = "0"
            )
            int page,

            @RequestParam(
                    defaultValue = "20"
            )
            int size) {

        PageResponse<FailoverResponse>response =
                failoverService.getAllFailovers( page,size);

        return ResponseEntity.ok( response );
    }


    @GetMapping("/source/{regionCode}")
    public ResponseEntity<
            PageResponse<FailoverResponse>>
    getFailoversBySourceRegion(

            @PathVariable
            String regionCode,

            @RequestParam(
                    defaultValue = "0"
            )
            int page,

            @RequestParam(
                    defaultValue = "20"
            )
            int size) {

        PageResponse<FailoverResponse>
                response =
                failoverService.getFailoversBySourceRegion(
                                regionCode,
                                page,
                                size
                        );

        return ResponseEntity.ok(
                response
        );
    }


    @GetMapping("/target/{regionCode}")
    public ResponseEntity< PageResponse<FailoverResponse>>
    getFailoversByTargetRegion(

            @PathVariable
            String regionCode,

            @RequestParam(
                    defaultValue = "0"
            )
            int page,

            @RequestParam(
                    defaultValue = "20"
            )
            int size) {


        PageResponse<FailoverResponse>
                response =
                failoverService.getFailoversByTargetRegion(
                                regionCode,
                                page,
                                size
                        );


        return ResponseEntity.ok(
                response
        );
    }
	
}
