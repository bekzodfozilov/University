package com.example.univercity.service;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.CustemDto.GroupC;
import com.example.univercity.dto.modelDto.GroupDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    ResponseDto<Page<GroupDto>> getGroup(Integer page, Integer size);

    ResponseDto<GroupDto> add(GroupDto groupDto);

    ResponseDto<GroupDto> update(GroupDto groupDto);

    ResponseDto<GroupDto> delete(Integer id);

    ResponseDto<List<GroupC>> getId(Integer id);
}
