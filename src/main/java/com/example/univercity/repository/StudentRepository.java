package com.example.univercity.repository;

import com.example.univercity.dto.modelDto.CustemDto.StudentC;
import com.example.univercity.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Optional<Student> existsStudentByName(String name);

    @Query(value = "select s.name Name, u.name UniversityName, f.name FacultyName, g.name GroupName from Student s\n" +
            "            join Groups g on s.group_id = g.id\n" +
            "            join faculty f on g.faculty_id = f.id\n" +
            "            join university u on f.university_id = u.id\n" +
            "            where s.name = ?1", nativeQuery = true)
    StudentC findStudentInfo(String name);

}
