package br.com.feltex.user_api.controller;

import br.com.feltex.user_api.dto.school.SchoolDto;
import br.com.feltex.user_api.entity.SchoolEntity;
import br.com.feltex.user_api.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/school")
@RestController
public class SchoolController {
    @Autowired
    SchoolRepository schoolRepository;

    @PostMapping()
    public ResponseEntity addSchool (@RequestBody SchoolDto schoolDto) {
        try {
            SchoolEntity school = new SchoolEntity();
            school.setName(schoolDto.getName());
            schoolRepository.save(school);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
