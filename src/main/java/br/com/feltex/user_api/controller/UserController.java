package br.com.feltex.user_api.controller;

import br.com.feltex.user_api.dto.user.UpdateMultiDto;
import br.com.feltex.user_api.entity.SchoolsEntity;
import br.com.feltex.user_api.repository.SchoolRepository;
import br.com.feltex.user_api.utils.UserUtil;
import br.com.feltex.user_api.dto.user.UserDto;
import br.com.feltex.user_api.entity.UsersEntity;
import br.com.feltex.user_api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SchoolRepository schoolRepository;

//    get user list and search
    @GetMapping("")
    public List<UsersEntity> getUser(
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "") String username
    ) {
        return userRepository.findByEmailContainingAndUsernameContaining(email, username);
    }

//    get user by id
    @GetMapping("/{id}")
    public Optional<UsersEntity> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    //    add user
    @PostMapping("")
    public ResponseEntity postUser(@RequestBody UserDto userDto) {
        try {
            UsersEntity user = new UsersEntity();
            user.setEmail(userDto.getEmail());
            user.setUsername(userDto.getUsername());
            SchoolsEntity schoolById = schoolRepository.findById(userDto.getSchool()).orElseThrow();
            user.setSchool(schoolById);
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    //   update user
    @PatchMapping("/{id}")
    public ResponseEntity<UsersEntity> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        if(!userRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UsersEntity userUpdate = userRepository.getById(id);

        log.info("check log {}", dto);
        BeanUtils.copyProperties(dto, userUpdate, UserUtil.getNullPropertyNames(dto));

        log.info("user updated {}", userUpdate);
        UsersEntity userSave = userRepository.save(userUpdate);
        return new ResponseEntity<>(userSave, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        System.out.println(id);
        if(!userRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    create multiple user
    @PostMapping("/multiple")
    public ResponseEntity postMultiUser(@RequestBody UsersEntity[] listUser) {
        try {
            userRepository.saveAll(Arrays.asList(listUser));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    //    update multiple user by list id
    @PatchMapping("/multiple")
    public ResponseEntity patchMultiUser(@RequestBody UpdateMultiDto updateMultiDto) {
        try {
            List<UsersEntity> users = userRepository.findAllById(updateMultiDto.getIds());
            for (UsersEntity user: users) {
                user.setUsername(updateMultiDto.getUsername());
            }
            userRepository.saveAll(users);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
