package com.example.univercity.dto.modelDto;

import com.example.univercity.helper.AppMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private Integer id;

    @NotBlank(message = AppMessage.EMPTY_FIELD)
    private String name;

    @NotNull(message = AppMessage.EMPTY_FIELD)
    private Date year;

    @NotNull(message = AppMessage.EMPTY_FIELD)
    private FacultyDto faculty;

    private List<StudentDto> students;

    private JournalDto journal;

    private List<SubjectDto> subjects;
}
