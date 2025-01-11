package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.ez.fastfood.application.usecases.CustomerUseCase;
import br.com.fiap.ez.fastfood.application.usecases.OrderUseCase;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.application.usecases.ProductUseCase;
import br.com.fiap.ez.fastfood.domain.repository.CategoryRepository;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.domain.repository.ProductRepository;

@Configuration
public class UseCaseConfiguration {

	
	@Bean
	public OrderUseCase orderUseCase(OrderRepository orderRepository) {
		return new OrderUseCase(orderRepository);
	}
}
