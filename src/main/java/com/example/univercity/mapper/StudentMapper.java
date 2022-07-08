package com.example.univercity.mapper;

import com.example.univercity.dto.modelDto.GroupDto;
import com.example.univercity.dto.modelDto.MarkDto;
import com.example.univercity.dto.modelDto.StudentDto;
import com.example.univercity.model.Group;
import com.example.univercity.model.Mark;
import com.example.univercity.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentDto toUniversityDto(Student student){
        if(student == null) return null;
        StudentDto studentDto = new StudentDto();

        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setGroup(null);
        studentDto.setMarkList(markUniversityDtoList(student.getMarkList()));

        return studentDto;
    }

    protected static List<MarkDto> markUniversityDtoList(List<Mark> list){
        if(list == null) return null;
        return list
                .stream()
                .map(MarkMapper::toUniversityDto)
                .collect(Collectors.toList());
    }

    public static StudentDto toStudentDto(Student student){
        if(student == null) return null;

        StudentDto studentDto = new StudentDto();

        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setGroup(GroupMapper.toStudentDto(student.getGroup()));
        studentDto.setMarkList(markUniversityDtoList(student.getMarkList()));

        return studentDto;
    }

    public static Student toStudentEntity(StudentDto studentDto){
        if(studentDto == null) return null;

        Student student = new Student();

        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setGroup(GroupMapper.toStudentEntity(studentDto.getGroup()));

        return student;
    }

}
