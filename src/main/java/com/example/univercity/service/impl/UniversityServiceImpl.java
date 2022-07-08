package com.example.univercity.service.impl;


import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.ValidatorDto;
import com.example.univercity.dto.modelDto.UniversityDto;
import com.example.univercity.helper.AppCode;
import com.example.univercity.helper.AppMessage;
import com.example.univercity.mapper.UniversityMapper;

import com.example.univercity.model.University;
import com.example.univercity.repository.UniversityRepository;
import com.example.univercity.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    private final ValidatorService validatorService;

    @Override
    public ResponseDto<Page<UniversityDto>> getAll(Integer page, Integer size) {

        try {
            Page<University> universityPage = universityRepository.findAll(PageRequest.of(page, size));
            List<UniversityDto> universityDtoList = universityPage
                    .stream()
                    .map(UniversityMapper::toUniversityDto)
                    .collect(Collectors.toList());
            Page<UniversityDto> universityDtoPage = new PageImpl<>(universityDtoList, universityPage.getPageable(), universityPage.getTotalElements());
            return new ResponseDto<>(true, AppCode.OK, AppMessage.OK, universityDtoPage);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<UniversityDto> add(UniversityDto universityDto) {
        universityDto.setId(null);
        List<ValidatorDto> errors = validatorService.validUniversity(universityDto);
        if(errors.size() > 0)
            return new ResponseDto<>(false,AppCode.VALIDATOR_ERROR,AppMessage.VALIDATOR_MESSAGE,universityDto,errors);
        try {
            University university = UniversityMapper.EntityUniversity(universityDto);
            universityRepository.save(university);
            return new ResponseDto<>(true, AppCode.OK, AppMessage.SAVED, UniversityMapper.toUniversityDto(university));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR, universityDto);
        }
    }

    @Override
    public ResponseDto<UniversityDto> delete(Integer university_id) {
        List<ValidatorDto> errors = validatorService.validId(university_id);
        if(errors.size() > 0)
            return new ResponseDto<>(false,AppCode.VALIDATOR_ERROR,AppMessage.VALIDATOR_MESSAGE,errors);
        try {
            Optional<University> optionalUniversity = universityRepository.findById(university_id);
            universityRepository.delete(optionalUniversity.get());
            UniversityDto universityDto = UniversityMapper.toUniversityDto(optionalUniversity.get());
            return new ResponseDto<>(true,AppCode.OK,AppMessage.OK,universityDto);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDto<>(false,AppCode.DATABASE_ERROR,AppMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<UniversityDto> update(UniversityDto universityDto) {
        List<ValidatorDto> errors = validatorService.validId(universityDto.getId());
        if (errors.size() > 0)
            return new ResponseDto<>(false,AppCode.VALIDATOR_ERROR,AppMessage.VALIDATOR_MESSAGE,errors);
        try {
            University university = UniversityMapper.EntityUniversity(universityDto);
            universityRepository.save(university);
            UniversityDto universityDto1 = UniversityMapper.toUniversityDto(university);
            return new ResponseDto<>(true,AppCode.OK,AppMessage.SAVED,universityDto1);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDto<>(false,AppCode.DATABASE_ERROR,AppMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<UniversityDto> getId(Integer university_id) {
        List<ValidatorDto> errors = validatorService.validId(university_id);
        if(errors.size() > 0)
            return new ResponseDto<>(false,AppCode.VALIDATOR_ERROR,AppMessage.VALIDATOR_MESSAGE,errors);
        try {
            Optional<University> optionalUniversity = universityRepository.findById(university_id);
            return new ResponseDto<>(true,AppCode.OK,AppMessage.OK,UniversityMapper.toUniversityDto(optionalUniversity.get()));
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseDto<>(false,AppCode.DATABASE_ERROR,AppMessage.DATABASE_ERROR);
        }
    }
}
