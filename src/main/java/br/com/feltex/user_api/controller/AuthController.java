package br.com.feltex.user_api.controller;

import br.com.feltex.user_api.dto.auth.AuthDto;
import br.com.feltex.user_api.repository.UserRepository;
import br.com.feltex.user_api.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDto authDto) {
        if(userRepository.existsByEmail(authDto.getEmail())){
            Object data = JwtTokenUtil.generateToken(authDto);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
