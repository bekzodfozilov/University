package com.example.univercity.rest;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.CustemDto.StudentC;
import com.example.univercity.dto.modelDto.StudentDto;
import com.example.univercity.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentResource{

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseDto<Page<StudentDto>> getStudent(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20")Integer size){
      return studentService.get(page,size);
    }

    @PostMapping("/add")
    public ResponseDto<StudentDto> addStudent(@RequestBody @Valid StudentDto studentDto){
        return studentService.add(studentDto);
    }

    @PutMapping("/update")
    public ResponseDto<StudentDto> updateStudent(@RequestBody @Valid StudentDto studentDto){
        return studentService.update(studentDto);
    }
    @DeleteMapping("/delete")
    public ResponseDto<StudentDto> delete(@RequestParam Integer id){
        return studentService.delete(id);
    }
    @GetMapping("/get_student")
    public ResponseDto<StudentC> getStudent(@RequestParam String name){
        return studentService.getName(name);
    }
}
