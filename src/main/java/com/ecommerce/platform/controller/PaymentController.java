package com.ecommerce.platform.controller;

import com.ecommerce.platform.model.Payment;
import com.ecommerce.platform.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/process")
    public Payment processPayment(@RequestParam Long orderId, @RequestParam Double amount) {
        return paymentService.processPayment(orderId, amount);
    }

    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }

}