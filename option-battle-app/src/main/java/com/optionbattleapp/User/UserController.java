package com.optionbattleapp.User;

import com.optionbattleapp.DTOs.UserDTO;
import com.optionbattleapp.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/save")
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }
}
