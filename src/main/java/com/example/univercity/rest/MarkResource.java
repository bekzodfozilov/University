package com.example.univercity.rest;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.CustemDto.SubjectC;
import com.example.univercity.model.Student;
import com.example.univercity.repository.MarkRepository;
import com.example.univercity.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mark")
public class MarkResource {

    private final StudentRepository studentRepository;

    private final MarkRepository markRepository;

    @GetMapping
    public ResponseDto<List<?>> get(@RequestParam Integer studentId){
        Student student = studentRepository.findById(studentId).get();
        return new ResponseDto<>(true, 0, student.getName(), markRepository.getInfoStudent(studentId));
    }
}
