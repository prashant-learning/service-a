package com.learn.service.servicea.service;

import com.learn.service.servicea.httpclient.StudentRestClient;
import com.learn.service.servicea.model.Student;
import com.learn.service.servicea.response.GetStudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProxyService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StudentRestClient studentRestClient;


    // one way to get the data getForEntity
    public ResponseEntity<GetStudentResponse> getStudentByRollNumber(int rollNumber){

        String url = "http://localhost:6666/api/v1/student/" + rollNumber;

        //return restTemplate.getForEntity(url, Student[].class);
        try {
            ResponseEntity<GetStudentResponse> studentResponseEntity = restTemplate.getForEntity(url, GetStudentResponse.class);

            return studentResponseEntity;
        } catch (Exception exception){

            System.out.println(exception.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // one way to get the data getForObject

    public GetStudentResponse getStudentByRollNumberByObject(int rollNumber){

        String url = "http://localhost:6666/api/v1/student/" + rollNumber;

        return restTemplate.getForObject(url,GetStudentResponse.class );

    }

    public GetStudentResponse getStudentByRollNumberByExchange(int rollNumber){
        // create headers

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

      //  String url = "http://localhost:6666/api/v1/student/{rollNumber}";

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("rollNumber", String.valueOf(rollNumber));

        try {
           // ResponseEntity<GetStudentResponse> studentResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, GetStudentResponse.class, uriVariables);
            ResponseEntity<GetStudentResponse> studentResponseEntity = studentRestClient.applyGetExchange("{rollNumber}",requestEntity, uriVariables );

           if(studentResponseEntity.getStatusCode().is2xxSuccessful()){
               return studentResponseEntity.getBody();
           } else {
               return null;
           }
        } catch (HttpClientErrorException exception){
            return null;
        }
    }


    public String deleteStudentByRollNumberUsingExchange(int rollNumber){
        // create headers

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);


        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("rollNumber", String.valueOf(rollNumber));

        String url = "http://localhost:6666/api/v1/student/{rollNumber}";
        try {
             ResponseEntity<String> studentResponseEntity = restTemplate.
                     exchange(url, HttpMethod.DELETE ,requestEntity, String.class, uriVariables );

            if(studentResponseEntity.getStatusCode().is2xxSuccessful()){
                return Objects.requireNonNull(studentResponseEntity.getBody()).toString();
            } else {
                return null;
            }
        } catch (HttpClientErrorException exception){
            return null;
        }
    }


    public Student addStudentByRollNumberUsingExchange(Student student){
        // create headers

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Student> requestEntity = new HttpEntity<Student>(student, headers);


        Map<String, String> uriVariables = new HashMap<>();

        String url = "http://localhost:6666/api/v1/student/add";
        try {
            ResponseEntity<Student> studentResponseEntity = restTemplate.
                    exchange(url, HttpMethod.POST ,requestEntity, Student.class, uriVariables );

            if(studentResponseEntity.getStatusCode().is2xxSuccessful()){
                return Objects.requireNonNull(studentResponseEntity.getBody());
            } else {
                return null;
            }
        } catch (HttpClientErrorException exception){
            return null;
        }
    }
}
