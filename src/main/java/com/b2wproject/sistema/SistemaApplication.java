package com.b2wproject.sistema;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SistemaApplication implements CommandLineRunner{
	

	public static void main(String[] args) {
		SpringApplication.run(SistemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:55.0) Gecko/20100101 Firefox/55.0");
		String url = "https://swapi.co/api/planets?search=Dagobah";
		
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);
        
        if(response.getStatusCode()==HttpStatus.OK) {
        	
        	ObjectMapper mapper = new ObjectMapper();
        	JsonNode rootNode = mapper.readTree(response.getBody());
        	JsonNode locatedNode = rootNode.path("results").findValue("films");
        	List<String> list = mapper.readValue(locatedNode.toString(), new TypeReference<List<String>>(){});
        	System.out.println(list.size());
        	
        }else {
        	System.out.println("Erro");
        }

	}
}
