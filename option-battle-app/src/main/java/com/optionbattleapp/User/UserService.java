package com.optionbattleapp.User;

import com.optionbattleapp.DTOs.UserDTO;
import com.optionbattleapp.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(UserDTO userDto) {
        userDto.setPassword(passwordEncoder
                .encode(userDto.getPassword()));
        User newUser = new User(userDto);
        newUser.setRegisteredOn(new Timestamp(new Date().getTime()));
        return userRepository.save(newUser);
    }
}
