package com.example.univercity.service.impl;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.CustemDto.StudentC;
import com.example.univercity.dto.modelDto.StudentDto;
import com.example.univercity.helper.AppCode;
import com.example.univercity.helper.AppMessage;
import com.example.univercity.mapper.StudentMapper;
import com.example.univercity.model.Group;
import com.example.univercity.model.Student;
import com.example.univercity.repository.GroupRepository;
import com.example.univercity.repository.StudentRepository;
import com.example.univercity.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final GroupRepository groupRepository;

    @Override
    public ResponseDto<Page<StudentDto>> get(Integer page, Integer size) {
        try {
            Page<Student> students = studentRepository.findAll(PageRequest.of(page, size));
            List<StudentDto> studentDtoList = students.
                    stream()
                    .map(StudentMapper::toStudentDto)
                    .collect(Collectors.toList());
            Page<StudentDto> studentDtoPage = new PageImpl<>(studentDtoList, students.getPageable(), students.getTotalElements());
            return new ResponseDto<>(true, AppCode.OK, AppMessage.OK, studentDtoPage);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<StudentDto> add(StudentDto studentDto) {
        if (groupRepository.existsById(studentDto.getGroup().getId())) {
            try {
                studentDto.setId(null);
                Student student = StudentMapper.toStudentEntity(studentDto);
                studentRepository.save(student);
                return new ResponseDto<>(true, AppCode.OK, AppMessage.SAVED, studentDto);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
            }
        }
        return new ResponseDto<>(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND);
    }

    @Override
    public ResponseDto<StudentDto> update(StudentDto studentDto) {
        if (studentRepository.existsById(studentDto.getId()) && groupRepository.existsById(studentDto.getGroup().getId())) {
            try {
                Student student = StudentMapper.toStudentEntity(studentDto);
                studentRepository.save(student);
                StudentDto studentDto1 = StudentMapper.toStudentDto(studentRepository.findById(studentDto.getId()).get());
                return new ResponseDto<>(true, AppCode.OK, AppMessage.OK, studentDto1);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
            }
        }
        return new ResponseDto<>(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND);
    }

    @Override
    public ResponseDto<StudentDto> delete(Integer id) {
      if(studentRepository.existsById(id)){
          try {
              Optional<Student> optionalStudent= studentRepository.findById(id);
              Student student = optionalStudent.get();
              studentRepository.delete(student);
              student.setMarkList(new ArrayList<>());
              StudentDto studentDto = StudentMapper.toStudentDto(student);
              return new ResponseDto<>(true, AppCode.OK, AppMessage.OK, studentDto);
          }catch (Exception e){
              e.printStackTrace();
              return new ResponseDto<>(false,AppCode.DATABASE_ERROR,AppMessage.DATABASE_ERROR);
          }
      }
      return new ResponseDto<>(false,AppCode.NOT_FOUND,AppMessage.NOT_FOUND);
    }

    @Override
    public ResponseDto<StudentC> getName(String name) {
      Optional<Student> student = studentRepository.existsStudentByName(name);
      if(student.isPresent()){
           return new ResponseDto<>(true,AppCode.OK,AppMessage.OK,studentRepository.findStudentInfo(name));
        }
        return new ResponseDto<>(false,AppCode.NOT_FOUND,AppMessage.NOT_FOUND);
    }
}
