package org.joycat.controller;

import org.joycat.entity.UserOnline;
import org.joycat.service.UserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserOnlineController {

    @Autowired
    private UserOnlineService userOnlineService;

    @PostMapping("/online")
    public void userTurnOnline(@RequestBody UserOnline userOnline) {
        userOnlineService.turnOnline(userOnline);
    }

    @DeleteMapping("/offline")
    public void userTurnOffline(@RequestBody UserOnline userOnline) {
        userOnlineService.turnOffline(userOnline);
    }

}
