package com.java.springBoot.backend.Controller;

import com.java.springBoot.backend.Model.PlanType;
import com.java.springBoot.backend.Model.Users;
import com.java.springBoot.backend.Response.PaymentLinkResponse;
import com.java.springBoot.backend.Service.UserService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Autowired
    private UserService userService;

    @PostMapping("/{planType}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable PlanType planType, @RequestHeader("Authorization") String Jwt) throws Exception {

        Users user = userService.findUserProfileByJwt(Jwt);

        int amount = 50;

        if (planType == PlanType.ANNUALLY) {
            amount = amount * 100;
            amount = (int) (amount * 0.7);
        }

        RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

        JSONObject paymentLinkResponse = new JSONObject();

        paymentLinkResponse.put("amount", amount);
        paymentLinkResponse.put("currency", "EUR");

        JSONObject customer = new JSONObject();
        customer.put("name", user.getFullName());
        customer.put("email", user.getEmail());

        paymentLinkResponse.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", true);

        paymentLinkResponse.put("notify", notify);

        paymentLinkResponse.put("callback_url", "http://localhost:5173/upgrade_plan/success/planType" + planType);

        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkResponse);

        String paymentLinkId = paymentLink.get("id");
        String paymentLinkUrl = paymentLink.get("short_url");

        PaymentLinkResponse response = new PaymentLinkResponse();
        response.setPaymentLinkId(paymentLinkId);
        response.setPaymentLinkURL(paymentLinkUrl);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
