package com.learn.service.servicea.controller;

import com.learn.service.servicea.model.Student;
import com.learn.service.servicea.response.GetStudentResponse;
import com.learn.service.servicea.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proxy/api/v1")
public class PublicController {

    @Autowired
    private ProxyService service;

    @GetMapping("/student/{rollNumber}")
    public ResponseEntity<GetStudentResponse> getStudentByRollNumber(@PathVariable int rollNumber){

        return service.getStudentByRollNumber(rollNumber);

    }

    @GetMapping("/student/object/{rollNumber}")
    public ResponseEntity<GetStudentResponse> getStudentObjectByRollNumber(@PathVariable int rollNumber){

       return ResponseEntity.ok(service.getStudentByRollNumberByObject(rollNumber));

    }

    @GetMapping("/student/exchange/{rollNumber}")
    public ResponseEntity<GetStudentResponse> getStudentExchangeByRollNumber(@PathVariable int rollNumber){

        return ResponseEntity.ok(service.getStudentByRollNumberByExchange(rollNumber));

    }

    @DeleteMapping("/student/{rollNumber}")
    public ResponseEntity<String> deleteStudentExchangeByRollNumber(@PathVariable int rollNumber){

        return ResponseEntity.ok(service.deleteStudentByRollNumberUsingExchange(rollNumber));

    }

    @PostMapping("/student/add")
    public ResponseEntity<Student> addStudentExchange(@RequestBody Student student){

        return ResponseEntity.ok(service.addStudentByRollNumberUsingExchange(student));

    }

}
