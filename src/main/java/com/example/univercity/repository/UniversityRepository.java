package com.example.univercity.repository;

import com.example.univercity.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University,Integer> {

    boolean existsUniversitiesByName(String name);

    boolean existsUniversitiesById(Integer id);
}
