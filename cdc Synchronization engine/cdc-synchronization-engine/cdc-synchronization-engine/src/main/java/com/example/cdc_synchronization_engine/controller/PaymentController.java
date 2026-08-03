package com.example.cdc_synchronization_engine.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cdc_synchronization_engine.dto.PaymentRequest;
import com.example.cdc_synchronization_engine.dto.PaymentResponse;
import com.example.cdc_synchronization_engine.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response =
                paymentService.createPayment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PaymentResponse>
    updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        PaymentResponse response =
                paymentService.updatePaymentStatus(
                        id,
                        status);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(
            @PathVariable Long id) {

        PaymentResponse response =
                paymentService.getPayment(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>>
    getAllPayments() {

        List<PaymentResponse> response =
                paymentService.getAllPayments();

        return ResponseEntity.ok(response);
    }
}