package com.azienda.springRestTemplate.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.azienda.springRestTemplate.clientRest.ClientRest;
import com.azienda.springRestTemplate.model.Prodotto;

@SpringBootApplication(scanBasePackages = "com.azienda.springRestTemplate.clientRest")
public class SpringRestTemplateApplication {

	public static void main(String[] args) {
		try(ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringRestTemplateApplication.class, args)){
			ClientRest clientRest = applicationContext.getBean("clientRest",ClientRest.class);
			clientRest.cancellazionePerPrezzoCompreso(0F,1_000F);
			clientRest.getAll();
			clientRest.inserimento(new Prodotto("Jeans",50F));
			clientRest.inserimento(new Prodotto("Scarpe",75F));
			clientRest.inserimento(new Prodotto("Maglietta",45F));
			clientRest.getAll();
			clientRest.getById(15);
			clientRest.getByNome("jea");
			clientRest.getByPrezzo(50F);
			clientRest.getByNomeEPrezzo("jea",55F);
			clientRest.aggiornamentoCompleto(new Prodotto("Jeans2",35F),"Jeans");
			clientRest.getAll();
			clientRest.aggiornamentoParziale(new Prodotto(null,55F),"Jeans2");
			clientRest.getAll();
			clientRest.cancellazione(1);
			clientRest.getAll();
			clientRest.cancellazionePerPrezzoCompreso(55F,100F);
			clientRest.getAll();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}