package com.example.univercity.mapper;

import com.example.univercity.dto.modelDto.MarkDto;
import com.example.univercity.model.Mark;

public class MarkMapper {
    public static MarkDto toUniversityDto(Mark mark){
        if(mark == null) return null;

        MarkDto markDto = new MarkDto();

        markDto.setId(mark.getId());
        markDto.setStudent(null);
        markDto.setSubject(null);
        markDto.setMark(mark.getMark());
        markDto.setDate(mark.getDate());

        return markDto;
    }
}
