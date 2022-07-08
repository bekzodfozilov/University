package com.example.univercity.dto.modelDto;

import com.example.univercity.dto.modelDto.FacultyDto;
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

public class UniversityDto {

    private Integer id;

    @NotBlank(message = AppMessage.EMPTY_FIELD)
    private String name;

    @NotBlank(message = AppMessage.EMPTY_FIELD)

    private String address;
    @NotNull(message = AppMessage.EMPTY_FIELD)

    private Date openYear;

    private List<FacultyDto> faculties;


}
