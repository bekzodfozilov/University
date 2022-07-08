package com.example.univercity.repository;

import com.example.univercity.dto.modelDto.CustemDto.FacultyC;
import com.example.univercity.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

    boolean existsFacultiesByName(String name);

    Optional<Faculty> findByName(String name);

    @Query(name = "getId",value = "select count(faculty.name) as group_count , count(s.name) as student_count from faculty\n" +
            "   join groups g on faculty.id = g.faculty_id join student s on g.id = s.group_id where faculty.id = ?1", nativeQuery = true)
    FacultyC getId(Integer faculty_id);


}
