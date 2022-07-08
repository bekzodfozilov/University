package com.example.univercity.service;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.FacultyDto;
import com.example.univercity.dto.modelDto.UniversityDto;
import com.example.univercity.model.University;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public interface UniversityService {


    ResponseDto<Page<UniversityDto>> getAll(Integer page, Integer size);

    ResponseDto<UniversityDto> add(UniversityDto universityDto);

    ResponseDto<UniversityDto> delete(Integer university_id);

    ResponseDto<UniversityDto> update(UniversityDto universityDto);

    ResponseDto<UniversityDto> getId(Integer university_id);
}
