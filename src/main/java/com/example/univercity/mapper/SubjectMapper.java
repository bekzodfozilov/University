package com.example.univercity.mapper;

import com.example.univercity.dto.modelDto.SubjectDto;
import com.example.univercity.model.Subject;

public class SubjectMapper {

    public static SubjectDto toUniversityDto(Subject subject){
        if(subject == null) return null;
        SubjectDto subjectDto = new SubjectDto();

        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setGroups(null);
        subjectDto.setJournals(null);
        subjectDto.setJournals(null);

        return subjectDto;
     }

}
