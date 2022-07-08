package com.example.univercity.dto.modelDto;

import com.example.univercity.dto.modelDto.GroupDto;
import com.example.univercity.dto.modelDto.SubjectDto;
import com.example.univercity.helper.AppMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalDto {

    private Integer id;

    @NotBlank(message = AppMessage.EMPTY_FIELD)

    private String name;

    @NotNull(message = AppMessage.EMPTY_FIELD)
    private GroupDto group;

    private List<SubjectDto> subjects;
}

