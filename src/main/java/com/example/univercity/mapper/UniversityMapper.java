package com.example.univercity.mapper;

import com.example.univercity.dto.modelDto.FacultyDto;
import com.example.univercity.dto.modelDto.UniversityDto;
import com.example.univercity.model.Faculty;
import com.example.univercity.model.University;

import java.util.List;
import java.util.stream.Collectors;

public class UniversityMapper {

    public static UniversityDto toUniversityDto(University university){
        if(university == null) return null;

        UniversityDto universityDto = new UniversityDto();
        universityDto.setId(university.getId());
        universityDto.setName(university.getName());
        universityDto.setAddress(university.getAddress());
        universityDto.setFaculties(facultyUniversityDtoList(university.getFaculties()));
        universityDto.setOpenYear(university.getOpenYear());

        return universityDto;
    }

    protected static List<FacultyDto> facultyUniversityDtoList(List<Faculty> list){
        if(list == null) return null;
        return list.
                stream().map(FacultyMapper::toUniversityDto).
                collect(Collectors.toList());
    }

    public static University EntityUniversity(UniversityDto universityDto) {
        if(universityDto == null) return null;

        University university = new University();
        university.setId(universityDto.getId());
        university.setName(universityDto.getName());
        university.setAddress(universityDto.getAddress());
        university.setOpenYear(universityDto.getOpenYear());

        return university;
    }

    public static UniversityDto toFacultyDto(University university) {
        if(university == null) return null;

        UniversityDto universityDto = new UniversityDto();

        universityDto.setId(university.getId());
        universityDto.setName(university.getName());
        universityDto.setAddress(university.getAddress());
        universityDto.setFaculties(null);

        return universityDto;
    }
}
