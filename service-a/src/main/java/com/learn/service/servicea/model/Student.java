package com.learn.service.servicea.model;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private int rollNumber;
    private int classRoom;
    private char section;
    private String name;
    private String fatherName;
    private String address;
    private String mobileNumber;
}
