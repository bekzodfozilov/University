package com.example.univercity.rest;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.FacultyDto;
import com.example.univercity.dto.modelDto.UniversityDto;
import com.example.univercity.service.UniversityService;
import com.sun.xml.bind.v2.runtime.reflect.Lister;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/university")
public class UniversityResource {

      private final UniversityService universityService;

      @GetMapping("get")
      public ResponseDto<Page<UniversityDto>> getUniversity(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "20") Integer size){
          return universityService.getAll(page,size);
      }
      @PostMapping("/add")
       public ResponseDto<UniversityDto> addUniversity(@RequestBody @Valid  UniversityDto universityDto){
          return universityService.add(universityDto);
      }

      @DeleteMapping("/delete")
       public ResponseDto<UniversityDto> deleteUniversity(@RequestParam Integer university_id){
          return universityService.delete(university_id);
      }

      @PutMapping("/update")
      public ResponseDto<UniversityDto> updateUniversity(@RequestBody UniversityDto universityDto){
          return universityService.update(universityDto);
      }
      @GetMapping("/getId")
      public ResponseDto<UniversityDto> getId(@RequestParam Integer university_id ){
          return universityService.getId(university_id);
      }
}
