package br.com.fiap.ez.fastfood.adapters.out.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fiap.ez.fastfood.application.dto.UserDTO;

@FeignClient(name = "userClient", url = "${microservices.user-url}")
public interface  UserHttpClient {
	
	 @GetMapping("/find-by-cpf/{cpf}")
	 UserDTO getUserByCpf(@PathVariable("cpf") String cpf);

}
