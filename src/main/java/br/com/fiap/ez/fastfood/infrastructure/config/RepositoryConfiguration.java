package br.com.fiap.ez.fastfood.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import br.com.fiap.ez.fastfood.adapters.out.repository.JpaOrderRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.OrderRepositoryImpl;
import br.com.fiap.ez.fastfood.domain.repository.OrderRepository;


@Configuration
public class RepositoryConfiguration {

	@Bean
	public OrderRepository orderRepository(JpaOrderRepository orderJpaRepository) {
		return new OrderRepositoryImpl(orderJpaRepository);
	}

}
