package com.example.univercity.service.impl;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.CustemDto.GroupC;
import com.example.univercity.dto.modelDto.GroupDto;
import com.example.univercity.helper.AppCode;
import com.example.univercity.helper.AppMessage;
import com.example.univercity.mapper.GroupMapper;
import com.example.univercity.model.Group;
import com.example.univercity.repository.GroupRepository;
import com.example.univercity.service.GroupService;
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
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public ResponseDto<Page<GroupDto>> getGroup(Integer page, Integer size) {
        try {
            Page<Group> groupPage = groupRepository.findAll(PageRequest.of(page, size));
            List<GroupDto> groupDtoList = groupPage
                    .stream()
                    .map(GroupMapper::toUniversityDto)
                    .collect(Collectors.toList());
            Page<GroupDto> groupDtoPage = new PageImpl<>(groupDtoList, groupPage.getPageable(), groupPage.getTotalElements());
            return new ResponseDto<>(true, AppCode.OK, AppMessage.OK, groupDtoPage);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<GroupDto> add(GroupDto groupDto) {
        if (groupRepository.existsGroupByFacultyId(groupDto.getFaculty().getId())) {
            if (groupRepository.existsGroupByName(groupDto.getName()))
                return new ResponseDto<>(false, AppCode.VALIDATOR_ERROR, AppMessage.NOT_UNIQUE);
            try {
                Group group = GroupMapper.toStudentEntity(groupDto);
                groupRepository.save(group);
                return new ResponseDto<>(true, AppCode.OK, AppMessage.SAVED, GroupMapper.toStudentDto(group));
            } catch (Exception e) {
                return new ResponseDto<>(false, AppCode.VALIDATOR_ERROR, AppMessage.DATABASE_ERROR);
            }
        }
        return new ResponseDto<>(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND);
    }

    @Override
    public ResponseDto<GroupDto> update(GroupDto groupDto) {
        if (groupRepository.existsGroupByFacultyId(groupDto.getFaculty().getId()) && groupRepository.existsById(groupDto.getId())) {
            try {
                Group group = GroupMapper.toStudentEntity(groupDto);
                groupRepository.save(group);
                return new ResponseDto<>(false, AppCode.OK, AppMessage.SAVED, GroupMapper.toStudentDto(group));
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
            }
        }
        return new ResponseDto<>(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND);
    }

    @Override
    public ResponseDto<GroupDto> delete(Integer id) {
        if (groupRepository.existsById(id)) {
            try {
                Optional<Group> optionalGroup = groupRepository.findById(id);
                groupRepository.delete(optionalGroup.get());
                return new ResponseDto<>(true, AppCode.OK, AppMessage.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseDto<>(false, AppCode.DATABASE_ERROR, AppMessage.DATABASE_ERROR);
            }
        }
        return new ResponseDto<>(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND);
    }

    @Override
    public ResponseDto<List<GroupC>> getId(Integer id) {
        if (groupRepository.existsById(id))
            return new ResponseDto<>(true, AppCode.OK, AppMessage.OK, groupRepository.getInfoGroup(id));
        return new ResponseDto<>(false, AppCode.NOT_FOUND, AppMessage.NOT_FOUND);
    }
}
