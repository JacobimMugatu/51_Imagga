package telran.imaga.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import telran.imaga.dto.ResponseDto;
import telran.imaga.dto.Tag;

public class ImagaClientAppl {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "==");
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		String url = "https://api.imagga.com/v1/tagging";
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("url", "https://www.thenational.ae/image/policy:1.763463:1535194489/epaselect-RUSSIA-FORUM-ARMY-2018-KALASHNIKOV.jpg")
				.queryParam("language", "ru");
		//String endpoints = "?url=https://www.thenational.ae/image/policy:1.763463:1535194489/epaselect-RUSSIA-FORUM-ARMY-2018-KALASHNIKOV.jpg";
		ResponseEntity<ResponseDto>response = restTemplate.exchange(builder.build().encode().toUri(), 
				HttpMethod.GET, requestEntity, ResponseDto.class);
		
		displayTags(response.getBody().getResults()[0].getTags());
	}

	private static void displayTags(Tag[] tags) {
		Arrays.stream(tags)
		
		.forEach(t -> System.out.println(t.getTag()+"->"+t.getConfidence()));
		
	}

}
