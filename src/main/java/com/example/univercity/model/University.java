package com.example.univercity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "university")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class University {
    @Id
    @GeneratedValue(generator = "university_id_seq")
    @SequenceGenerator(name = "university_id_seq", sequenceName = "university_id_seq", allocationSize = 1)
    private Integer id;

    private String name;

    private String address;

    @Column(name = "open_year")
    private Date openYear;

    @OneToMany(mappedBy = "university")
    private List<Faculty> faculties;

}
