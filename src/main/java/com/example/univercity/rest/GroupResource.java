package com.example.univercity.rest;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.CustemDto.GroupC;
import com.example.univercity.dto.modelDto.GroupDto;
import com.example.univercity.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupResource {

    private final GroupService groupService;

    @GetMapping("/get")
    public ResponseDto<Page<GroupDto>> get(@RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "20") Integer size ){
        return groupService.getGroup(page,size);
    }

    @PostMapping("/add")
    public ResponseDto<GroupDto> addGroup(@RequestBody @Valid GroupDto groupDto){
        return groupService.add(groupDto);
    }

    @PutMapping("/update")
    public ResponseDto<GroupDto> update(@RequestBody @Valid GroupDto groupDto){
        return groupService.update(groupDto);
    }
    @DeleteMapping
    public ResponseDto<GroupDto> delete(@RequestParam Integer id){
        return groupService.delete(id);
    }
    @GetMapping("/getId")
    public ResponseDto<List<GroupC>> getId(@RequestParam Integer id){
        return groupService.getId(id);
    }
}
