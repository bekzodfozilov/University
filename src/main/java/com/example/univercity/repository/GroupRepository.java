package com.example.univercity.repository;

import com.example.univercity.dto.modelDto.CustemDto.GroupC;
import com.example.univercity.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer> {

    boolean existsGroupByFacultyId(Integer id);

    boolean existsGroupByName(String name);

    @Query(value = "select s.name Name, su.name Subject, sum(m.mark) / count(m.mark) SredniyBall from Student s\n" +
            "            left join Mark m on m.student_id = s.id\n" +
            "            left join Subject su on m.subject_id = su.id\n" +
            "            where group_id = ?1\n" +
            "            group by su.name, s.name\n" +
            "        order by sredniyBall desc nulls last",nativeQuery = true)
    List<GroupC> getInfoGroup(Integer id);
}
