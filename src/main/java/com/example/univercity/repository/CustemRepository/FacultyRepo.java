package com.example.univercity.repository.CustemRepository;

import com.example.univercity.dto.modelDto.CustemDto.FacultyC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FacultyRepo {

    private final EntityManager entityManager;

    public List getFacultyCount(Integer faculty_id) {

        String s = "select faculty.name , count(faculty.name) as group_count , count(s.name) as student_count from faculty\n" +
                "   join groups g on faculty.id = g.faculty_id join student s on g.id = s.group_id where faculty.id = ?1 group by faculty.name";

        Query query = entityManager.createNativeQuery(s, FacultyC.class);

        query.setParameter(1, faculty_id);

        return query.getResultList();
    }
}
