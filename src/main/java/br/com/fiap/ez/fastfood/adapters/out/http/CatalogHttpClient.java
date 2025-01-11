package br.com.fiap.ez.fastfood.adapters.out.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.fiap.ez.fastfood.application.dto.CatalogDTO;

@FeignClient(name = "catalogClient", url = "${microservices.catalog-url}")
public interface CatalogHttpClient {
	
	@GetMapping("/catalog")
	 CatalogDTO findProductById(@RequestParam("id") Long id);

}
