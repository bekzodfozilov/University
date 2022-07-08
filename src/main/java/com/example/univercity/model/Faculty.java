package com.example.univercity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "faculty")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Faculty {
    @Id
    @GeneratedValue(generator = "faculty_id_seq")
    @SequenceGenerator(name = "faculty_id_seq", sequenceName = "faculty_id_seq", allocationSize = 1)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;


    @OneToMany(mappedBy = "faculty")
    private List<Group> groups;
}