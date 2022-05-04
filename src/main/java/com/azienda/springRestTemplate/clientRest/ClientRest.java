package com.azienda.springRestTemplate.clientRest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.azienda.springRestTemplate.model.Prodotto;

@Component
public class ClientRest {
	// comm.
	private final static String BASE_URL = "http://localhost:8080/rest/products";
	
	@Autowired
	private RestTemplate restTemplate;
	
	private HttpHeaders getBaseHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return httpHeaders;
	}

	public void getAll(){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Prodotto[]> responseEntity = restTemplate.exchange(BASE_URL + "/getAll",HttpMethod.GET,httpEntity,Prodotto[].class);
			if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
				Prodotto[] prodotti = responseEntity.getBody();
				Arrays.asList(prodotti).forEach(System.out::println);
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void getById(Integer id){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Prodotto> responseEntity = restTemplate.exchange(BASE_URL + "/getById/{id}",HttpMethod.GET,httpEntity,Prodotto.class,id);
			if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
				Prodotto prodotto = responseEntity.getBody();
				System.out.println(prodotto);
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void getByNome(String nome){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Prodotto[]> responseEntity = restTemplate.exchange(BASE_URL + "/getByNome/{nome}",HttpMethod.GET,httpEntity,Prodotto[].class,nome);
			if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
				Prodotto[] prodotto = responseEntity.getBody();
				Arrays.asList(prodotto).forEach(System.out::println);
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void getByPrezzo(Float prezzo){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Prodotto[]> responseEntity = restTemplate.exchange(BASE_URL + "/getByPrezzo/{prezzo}",HttpMethod.GET,httpEntity,Prodotto[].class,prezzo);
			if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
				Prodotto[] prodotto = responseEntity.getBody();
				Arrays.asList(prodotto).forEach(System.out::println);
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void getByNomeEPrezzo(String nome,Float prezzo){
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Prodotto[]> responseEntity = restTemplate.exchange(BASE_URL + "/getByNomeAndPrezzo/{nome}/{prezzo}",HttpMethod.GET,httpEntity,Prodotto[].class,nome,prezzo);
			if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
				Prodotto[] prodotto = responseEntity.getBody();
				Arrays.asList(prodotto).forEach(System.out::println);
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
		
	public void inserimento(Prodotto prodotto) {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<Prodotto> httpEntity = new HttpEntity<Prodotto>(prodotto,httpHeaders);
			ResponseEntity<Prodotto> responseEntity = restTemplate.exchange(BASE_URL + "/creaProdotto",HttpMethod.POST,httpEntity,Prodotto.class);
			if ( responseEntity.getStatusCode().equals(HttpStatus.CREATED) ) {
				Prodotto prod = responseEntity.getBody();
				System.out.println(prod);
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void aggiornamentoCompleto(Prodotto prodotto,String nome) {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Prodotto[]> responseEntity = restTemplate.exchange(BASE_URL + "/getByNome/{nome}",HttpMethod.GET,httpEntity,Prodotto[].class,nome);
			if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
				Prodotto[] prodotti = responseEntity.getBody();
				if( prodotti != null && prodotti.length == 1 ) {
					Prodotto prodottoDb = prodotti[0];
					prodotto.setId(prodottoDb.getId());
					HttpEntity<Prodotto> httpEntity2 = new HttpEntity<Prodotto>(prodotto,httpHeaders);
					ResponseEntity<Prodotto> responseEntity2 = restTemplate.exchange(BASE_URL + "/aggiornamentoCompleto",HttpMethod.PUT,httpEntity2,Prodotto.class);
					if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
						Prodotto prod = responseEntity2.getBody();
						System.out.println("Prodotto aggiornato (completamente): " + prod);
					}
				}
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void aggiornamentoParziale(Prodotto prodotto,String nome) {
		try {
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			restTemplate.setRequestFactory(requestFactory);
			
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Prodotto[]> responseEntity = restTemplate.exchange(BASE_URL + "/getByNome/{nome}",HttpMethod.GET,httpEntity,Prodotto[].class,nome);
			if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
				Prodotto[] prodotti = responseEntity.getBody();
				if( prodotti != null && prodotti.length == 1 ) {
					Prodotto prodottoDb = prodotti[0];
					prodotto.setId(prodottoDb.getId());
					HttpEntity<Prodotto> httpEntity2 = new HttpEntity<Prodotto>(prodotto,httpHeaders);
					ResponseEntity<Prodotto> responseEntity2 = restTemplate.exchange(BASE_URL + "/aggiornamentoParziale",HttpMethod.PATCH,httpEntity2,Prodotto.class);
					if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
						Prodotto prod = responseEntity2.getBody();
						System.out.println("Prodotto aggiornato (parzialmente): " + prod);
					}
				}
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void cancellazione(Integer id) {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Void> responseEntity = restTemplate.exchange(BASE_URL + "/cancellazione/{id}",
					HttpMethod.DELETE,httpEntity,Void.class,id);
			if ( responseEntity.getStatusCode().equals(HttpStatus.NO_CONTENT) ) {
				System.out.println("Cancellazione effettuata");
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void cancellazionePerPrezzoCompreso(Float prezzoMin,Float prezzoMax) {
		try {
			HttpHeaders httpHeaders = getBaseHeaders();
			HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
			ResponseEntity<Integer> responseEntity = restTemplate.exchange(BASE_URL + "/cancellazionePerPrezzoComreso/{start}/{end}",
					HttpMethod.DELETE,httpEntity,Integer.class,prezzoMin,prezzoMax);
			if ( responseEntity.getStatusCode().equals(HttpStatus.OK) ) {
				System.out.println("Cancellazione effettuata");
				System.out.println("Cancellati " + responseEntity.getBody() + " prodotti");
			}
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	}
	
}