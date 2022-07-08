package com.example.univercity.service.impl;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.ValidatorDto;
import com.example.univercity.dto.modelDto.CustemDto.FacultyC;
import com.example.univercity.dto.modelDto.FacultyDto;
import com.example.univercity.helper.AppCode;
import com.example.univercity.helper.AppMessage;

import com.example.univercity.mapper.FacultyMapper;
import com.example.univercity.mapper.UniversityMapper;
import com.example.univercity.model.Faculty;
import com.example.univercity.model.University;
import com.example.univercity.repository.CustemRepository.FacultyRepo;
import com.example.univercity.repository.FacultyRepository;
import com.example.univercity.repository.UniversityRepository;
import com.example.univercity.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    private final ValidatorService validatorService;

    private final UniversityRepository universityRepository;

    private final FacultyRepo facultyRepo;


    @Override
    public ResponseDto<Page<FacultyDto>> getAll(Integer page, Integer size) {
        try {

            Page<Faculty> facultyPage = facultyRepository.findAll(PageRequest.of(page, size));
            List<FacultyDto> facultyDtoList = facultyPage.stream().map(FacultyMapper::toFacultyDto).collect(Collectors.toList());
            Page<FacultyDto> facultyDtoPage = new PageImpl<>(facultyDtoList, facultyPage.getPageable(), facultyPage.getTotalElements());
            return new ResponseDto<>(true, AppCode.OK, AppMessage.OK, facultyDtoPage);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<FacultyDto> add(FacultyDto facultyDto) {
        facultyDto.setId(null);
        List<ValidatorDto> errors = validatorService.addFaculty(facultyDto);
        if (errors.size() > 0)
            return new ResponseDto<>(false, AppCode.VALIDATOR_ERROR, AppMessage.VALIDATOR_MESSAGE, facultyDto, errors);
        try {
            Faculty faculty = FacultyMapper.toEntityFaculty(facultyDto);

            facultyRepository.save(faculty);
            Optional<Faculty> optionalFaculty = facultyRepository.findByName(faculty.getName());
            return new ResponseDto<>(true, AppCode.OK, AppMessage.SAVED, FacultyMapper.toFacultyDto(optionalFaculty.get()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<FacultyDto> delete(Integer faculty_id) {
        List<ValidatorDto> errors = validatorService.validFacultyId(faculty_id);
        if (errors.size() > 0) return new ResponseDto<>(false, AppCode.VALIDATOR_ERROR, AppMessage.DATABASE_ERROR);
        Optional<Faculty> optionalFaculty = facultyRepository.findById(faculty_id);
        try {
            facultyRepository.delete(optionalFaculty.get());
            optionalFaculty.get().setGroups(new ArrayList<>());
            FacultyDto facultyDto = FacultyMapper.toFacultyDto(optionalFaculty.get());
            return new ResponseDto<>(true, AppCode.OK, AppMessage.OK, facultyDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<FacultyDto> update(FacultyDto facultyDto) {
        if (facultyRepository.existsById(facultyDto.getId()) && universityRepository.existsById(facultyDto.getUniversity().getId())) {
            try {
                Faculty toEntityFaculty = FacultyMapper.toEntityFaculty(facultyDto);
                Optional<University> university = universityRepository.findById(facultyDto.getUniversity().getId());
                facultyRepository.save(toEntityFaculty);
                facultyDto.setUniversity(UniversityMapper.toFacultyDto(university.get()));
                return new ResponseDto<>(true, AppCode.OK, AppMessage.SAVED, facultyDto);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
            }
        }
        return new ResponseDto<>(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND);
    }

    @Override
    public ResponseDto<List<FacultyC>> getId(Integer faculty_id) {
      if(facultyRepository.existsById(faculty_id)){
           List<FacultyC> facultyC = facultyRepo.getFacultyCount(faculty_id);
           return new ResponseDto<>(true,AppCode.OK,AppMessage.OK,facultyC);
      }
      return new ResponseDto<>(false,AppCode.NOT_FOUND,AppMessage.NOT_FOUND);

    }

}
