package org.joycat.controller;

import org.joycat.entity.User;
import org.joycat.entity.UserShort;
import org.joycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    // TODO: 1)Метод принятитя юзера , коорых входит в онлайн и обратный случай в офлайн.
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public Boolean addUser(@RequestBody User user) {
        return userService.addNewUser(userService.encryptUser(user));
    }

    @PostMapping("/login")
    public Boolean signInUser(@RequestBody UserShort userShort) {
        // TODO: get user IP from request
        String ip = "localhost";
        return true;
//        return userService.signInUser(userShort, ip);
    }

}
