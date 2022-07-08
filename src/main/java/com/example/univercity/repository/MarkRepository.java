package com.example.univercity.repository;

import com.example.univercity.dto.modelDto.CustemDto.SubjectC;
import com.example.univercity.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark,Integer> {

    @Query(value = "select sb from Subject sb\n" +
            "            join Mark ma on ma.subject_id = sb.id\n" +
            "            join Student st on ma.student_id = st.id\n" +
            "            where st.id = ?1",nativeQuery = true)
    List<SubjectC> getInfoStudent(Integer id);
}
