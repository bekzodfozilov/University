package com.example.univercity.dto.modelDto;

import com.example.univercity.helper.AppMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkDto {

    private Integer id;

    @NotNull(message = AppMessage.EMPTY_FIELD)

    private StudentDto student;
    @NotNull(message = AppMessage.EMPTY_FIELD)

    private SubjectDto subject;
    @NotNull(message = AppMessage.EMPTY_FIELD)

    private Integer mark;

    private Date date;
}
