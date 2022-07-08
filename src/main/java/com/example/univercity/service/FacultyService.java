package com.example.univercity.service;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.CustemDto.FacultyC;
import com.example.univercity.dto.modelDto.FacultyDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyService {

    ResponseDto<Page<FacultyDto>> getAll(Integer page, Integer size);

    ResponseDto<FacultyDto> add(FacultyDto facultyDto);

    ResponseDto<FacultyDto> delete(Integer faculty_id);

    ResponseDto<FacultyDto> update(FacultyDto facultyDto);

    ResponseDto<List<FacultyC>> getId(Integer faculty_id);
}
