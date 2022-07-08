package com.example.univercity.rest;

import com.example.univercity.dto.ResponseDto;
import com.example.univercity.dto.modelDto.CustemDto.FacultyC;
import com.example.univercity.dto.modelDto.FacultyDto;
import com.example.univercity.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculty")
public class FacultyResource {

    private final FacultyService facultyService;

    @GetMapping("/get")
    public ResponseDto<Page<FacultyDto>> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size){
        return facultyService.getAll(page,size);
    }
    @PostMapping("/add")
    public ResponseDto<FacultyDto> addFaculty(@RequestBody @Valid FacultyDto facultyDto){
        return facultyService.add(facultyDto);
    }

    @DeleteMapping("/delete")
    public ResponseDto<FacultyDto> deleteFaculty(@RequestParam Integer faculty_id){
        return facultyService.delete(faculty_id);
    }

    @PutMapping("/update")
    public ResponseDto<FacultyDto> updateFaculty(@RequestBody @Valid FacultyDto facultyDto){
        return facultyService.update(facultyDto);
    }

    @GetMapping("/getId")
    public ResponseDto<List<FacultyC>> getId(@RequestParam Integer faculty_id){
        return facultyService.getId(faculty_id);
    }

}
