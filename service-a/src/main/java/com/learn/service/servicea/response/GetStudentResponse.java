package com.learn.service.servicea.response;


import com.learn.service.servicea.model.Student;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentResponse {

    private Student student;
}
