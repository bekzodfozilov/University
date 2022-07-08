package com.example.univercity.mapper;

import com.example.univercity.dto.modelDto.*;
import com.example.univercity.model.Group;
import com.example.univercity.model.Student;
import com.example.univercity.model.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class GroupMapper {

    public static GroupDto toUniversityDto(Group group){
        if (group == null) return null;
        GroupDto groupDto = new GroupDto();

        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setYear(group.getYear());
        groupDto.setStudents(studentUniversityDtoList(group.getStudents()));
        groupDto.setJournal(JournalMapper.toUniversityDto(group.getJournal()));
        groupDto.setSubjects(subjectUniversityDtoList(group.getSubjects()));

        return groupDto;

    }

    private static List<StudentDto> studentUniversityDtoList(List<Student> list){
       return  list
                .stream()
                .map(StudentMapper::toUniversityDto)
                .collect(Collectors.toList());
    }
    private static List<SubjectDto> subjectUniversityDtoList(List<Subject> list){
        if(list == null) return null;
        return  list
                .stream()
                .map(SubjectMapper::toUniversityDto)
                .collect(Collectors.toList());
    }
    public static GroupDto toStudentDto(Group group){
        if (group == null) return null;

        GroupDto groupDto = new GroupDto();

        groupDto.setId(group.getId());
        groupDto.setName(group.getName());
        groupDto.setYear(group.getYear());
        groupDto.setStudents(null);
        groupDto.setJournal(JournalMapper.toUniversityDto(group.getJournal()));
        groupDto.setSubjects(subjectUniversityDtoList(group.getSubjects()));

        return groupDto;

    }
    public static Group toStudentEntity(GroupDto groupDto){
        if(groupDto == null) return null;

        Group group = new Group();

        group.setId(groupDto.getId());
        group.setName(groupDto.getName());
        group.setYear(groupDto.getYear());
        group.setFaculty(FacultyMapper.toEntityFaculty(groupDto.getFaculty()));
        group.setStudents(null);
        group.setSubjects(null);

        return group;
    }
}
