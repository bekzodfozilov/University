package com.example.univercity.service.impl;

import com.example.univercity.dto.ValidatorDto;
import com.example.univercity.dto.modelDto.FacultyDto;
import com.example.univercity.dto.modelDto.UniversityDto;
import com.example.univercity.helper.AppMessage;
import com.example.univercity.mapper.FacultyMapper;
import com.example.univercity.mapper.UniversityMapper;
import com.example.univercity.model.University;
import com.example.univercity.repository.FacultyRepository;
import com.example.univercity.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValidatorService {

    private final UniversityRepository universityRepository;

    private final FacultyRepository facultyRepository;

    public  List<ValidatorDto> validUniversity(UniversityDto universityDto) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(universityRepository.existsUniversitiesByName(universityDto.getName())){
            errors.add(new ValidatorDto("name", AppMessage.NOT_UNIQUE));
        }
        return errors;
    }

    public List<ValidatorDto> validId(Integer university_id) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(!universityRepository.existsUniversitiesById(university_id)){
            errors.add(new ValidatorDto("id",AppMessage.NOT_FOUND));
        }
        return errors;
    }

    public List<ValidatorDto> addFaculty(FacultyDto facultyDto) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(facultyRepository.existsFacultiesByName(facultyDto.getName())){
            errors.add(new ValidatorDto("name",AppMessage.NOT_UNIQUE));
        }
        if(!universityRepository.existsUniversitiesById(facultyDto.getUniversity().getId())){
            errors.add(new ValidatorDto("university id",AppMessage.NOT_FOUND));
        }
        Optional<University> university = universityRepository.findById(facultyDto.getUniversity().getId());
        facultyDto.setUniversity(UniversityMapper.toFacultyDto(university.get()));
        return errors;
    }

    public List<ValidatorDto> validFacultyId(Integer faculty_id) {
        List<ValidatorDto> errors = new ArrayList<>();
        if(!facultyRepository.existsById(faculty_id)){
            errors.add(new ValidatorDto("id",AppMessage.NOT_FOUND));
        }
        return errors;
    }
}
