package br.com.feltex.user_api.controller;

import br.com.feltex.user_api.dto.school.SchoolDto;
import br.com.feltex.user_api.dto.school.SchoolListDto;
import br.com.feltex.user_api.entity.SchoolsEntity;
import br.com.feltex.user_api.entity.UsersEntity;
import br.com.feltex.user_api.repository.SchoolRepository;
import br.com.feltex.user_api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/school")
@RestController
@Slf4j
public class SchoolController {
    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping()
    public ResponseEntity addSchool (@RequestBody SchoolDto schoolDto) {
        try {
            SchoolsEntity school = new SchoolsEntity();
            school.setName(schoolDto.getName());
            schoolRepository.save(school);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    @GetMapping()
    public ResponseEntity<List<SchoolListDto>> getSchool(){
        try{
            List<SchoolListDto> schoolList = new ArrayList<>();
            List<SchoolsEntity> schools = schoolRepository.findAll();
            List<Long> idSchools = schools.stream().map(SchoolsEntity::getSchoolId).collect(Collectors.toList());
            List<UsersEntity> usersBySchool = userRepository.findAllBySchoolIds(idSchools);
            Map<Long, List<UsersEntity>> groupUserBySchool = usersBySchool.stream()
                    .collect(Collectors.groupingBy(i->i.getSchool().getSchoolId()));

            for(SchoolsEntity schoolsEntity: schools) {
                SchoolListDto schoolListDto = new SchoolListDto();
                schoolListDto.setName(schoolsEntity.getName());
                schoolListDto.setId(schoolsEntity.getSchoolId());
                schoolListDto.setUsers(groupUserBySchool.get(schoolsEntity.getSchoolId()));
                schoolList.add(schoolListDto);
            }

            return new ResponseEntity<>(schoolList, HttpStatus.OK);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
