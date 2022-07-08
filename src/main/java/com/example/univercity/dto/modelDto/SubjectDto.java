package com.example.univercity.dto.modelDto;

import com.example.univercity.helper.AppMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private Integer id;

    @NotBlank(message = AppMessage.EMPTY_FIELD)

    private String name;

    private List<GroupDto> groups;

    private List<JournalDto> journals;

    private List<MarkDto> markList;

}
