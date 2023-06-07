package org.joycat.controller;

import org.joycat.entity.UserOnline;
import org.joycat.service.UserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserOnlineController {

    @Autowired
    private UserOnlineService userOnlineService;

    @PostMapping("/")
    public void userTurnOnline(@RequestBody UserOnline userOnline) {
        userOnlineService.turnOnline(userOnline);
    }

    @DeleteMapping("/offline")
    public void userTurnOffline(@RequestBody UserOnline userOnline) {
        userOnlineService.turnOffline(userOnline);
    }

    @GetMapping("/online")
    public ResponseEntity<List<String>> getOnlineUsers() {
        return new ResponseEntity<>(userOnlineService.getOnlineUsers(), HttpStatus.OK);
    }

}
