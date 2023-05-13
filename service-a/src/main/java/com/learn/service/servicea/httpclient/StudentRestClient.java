package com.learn.service.servicea.httpclient;

import com.learn.service.servicea.response.GetStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class StudentRestClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${student.service.a.url}")
    private String baseUrl;

    public ResponseEntity<Object> applyExchange(String url, HttpMethod method,  HttpEntity<?> requestEntity, Map<String,?> uriVariables){

        return restTemplate.exchange(baseUrl+url, method, requestEntity, Object.class, uriVariables);
    }

    public ResponseEntity<GetStudentResponse> applyGetExchange(String url, HttpEntity<?> requestEntity, Map<String,?> uriVariables){

        return restTemplate.exchange(baseUrl+url, HttpMethod.GET, requestEntity, GetStudentResponse.class, uriVariables);
    }

}
