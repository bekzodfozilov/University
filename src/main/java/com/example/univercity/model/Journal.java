package com.example.univercity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "journal")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Journal {
    @Id
    @GeneratedValue(generator = "journal_id_seq")
    @SequenceGenerator(name = "journal_id_seq", sequenceName = "journal_id_seq", allocationSize = 1)
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinTable(
            name = "journal_subject",
            joinColumns = @JoinColumn(name = "journal_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;
}