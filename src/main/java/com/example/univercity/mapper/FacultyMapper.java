package com.example.univercity.mapper;

import com.example.univercity.dto.modelDto.FacultyDto;
import com.example.univercity.dto.modelDto.GroupDto;
import com.example.univercity.dto.modelDto.UniversityDto;
import com.example.univercity.model.Faculty;
import com.example.univercity.model.Group;
import com.example.univercity.model.University;

import java.util.List;
import java.util.stream.Collectors;

public class FacultyMapper {

    public static FacultyDto toUniversityDto(Faculty faculty){
        if( faculty == null )
            return null;
       FacultyDto facultyDto = new FacultyDto();
       facultyDto.setId(faculty.getId());
       facultyDto.setName(faculty.getName());
       facultyDto.setUniversity(null);
       facultyDto.setGroups(groupUniversityDtoList(faculty.getGroups()));

       return facultyDto;
    }

    private static List<GroupDto> groupUniversityDtoList(List<Group> list){
        if(list == null) return null;
        return null;
//        return list.
//                stream()
//                .map(GroupMapper::toUniversityDto)
//                .collect(Collectors.toList());
    }

    public static FacultyDto toFacultyDto(Faculty faculty) {
        if(faculty == null) return null;

        FacultyDto facultyDto = new FacultyDto();

        facultyDto.setId(faculty.getId());
        facultyDto.setName(faculty.getName());
        facultyDto.setUniversity(UniversityMapper.toFacultyDto(faculty.getUniversity()));
        facultyDto.setGroups(groupUniversityDtoList(faculty.getGroups()));


        return facultyDto;
    }

    public static Faculty toEntityFaculty(FacultyDto facultyDto) {
        if(facultyDto == null) return null;

        Faculty faculty = new Faculty();

        faculty.setId(facultyDto.getId());
        faculty.setName(facultyDto.getName());
        faculty.setUniversity(UniversityMapper.EntityUniversity(facultyDto.getUniversity()));

        return faculty;
    }
}
