package com.example.univercity.dto.modelDto.CustemDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyC {

    @Id
    private String name;

    private Integer group_count;

    private Integer student_count;


}
