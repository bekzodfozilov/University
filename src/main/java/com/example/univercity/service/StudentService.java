package com.example.univercity.service;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.CustemDto.StudentC;
import com.example.univercity.dto.modelDto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    ResponseDto<Page<StudentDto>> get(Integer page, Integer size);

    ResponseDto<StudentDto> add(StudentDto studentDto);

    ResponseDto<StudentDto> update(StudentDto studentDto);

    ResponseDto<StudentDto> delete(Integer id);

    ResponseDto<StudentC> getName(String name);
}
