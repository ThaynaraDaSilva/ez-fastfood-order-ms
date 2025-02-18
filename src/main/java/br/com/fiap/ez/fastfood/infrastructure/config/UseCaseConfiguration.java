package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.ez.fastfood.adapters.out.http.CatalogHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.http.UserHttpClient;
import br.com.fiap.ez.fastfood.adapters.out.messaging.PaymentPublisher;
import br.com.fiap.ez.fastfood.application.usecases.OrderUseCase;

import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;


@Configuration
public class UseCaseConfiguration {

	
	@Bean
	public OrderUseCase orderUseCase(OrderRepository orderRepository, 
			CatalogHttpClient catalogHttpClient,
			UserHttpClient userHttpClient,
			PaymentPublisher paymentPublisher) {
		return new OrderUseCase(orderRepository,catalogHttpClient,userHttpClient,paymentPublisher);
	}
}