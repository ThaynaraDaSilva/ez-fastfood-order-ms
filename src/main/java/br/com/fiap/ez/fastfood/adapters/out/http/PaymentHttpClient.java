package br.com.fiap.ez.fastfood.adapters.out.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.fiap.ez.fastfood.application.dto.PaymentRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.PaymentResponseDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "paymentClient", url = "${microservices.payment-url}")
public interface PaymentHttpClient {
	
	 @PostMapping("/payment")
	  PaymentResponseDTO registerPayment(@RequestBody PaymentRequestDTO paymentRequest);

}
