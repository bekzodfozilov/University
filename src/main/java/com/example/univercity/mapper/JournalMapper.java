package com.example.univercity.mapper;

import com.example.univercity.dto.modelDto.JournalDto;
import com.example.univercity.model.Journal;

public class JournalMapper {

    public static JournalDto toUniversityDto(Journal journal){
        if(journal == null) return null;
        JournalDto journalDto = new JournalDto();

        journalDto.setId(journal.getId());
        journalDto.setName(journal.getName());
        journalDto.setGroup(null);
        journal.setSubjects(null);

        return journalDto;
    }
}
