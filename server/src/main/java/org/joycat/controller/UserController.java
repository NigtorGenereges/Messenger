package org.joycat.controller;

import org.joycat.entity.User;
import org.joycat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // TODO: 1)Метод принятитя юзера , коорых входит в онлайн и обратный случай в офлайн.
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public void addUser(User user) {
        userService.saveUser(userService.encryptUser(user));
    }




//    @PostMapping("/useroffline")
//    public void userOffline(User user) { userService.
//
//    }
//
//    @PostMapping("/useronline")
//    public void userOnline(User user) {
//
//    }

}
