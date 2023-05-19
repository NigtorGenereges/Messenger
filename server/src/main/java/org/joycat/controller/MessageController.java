package org.joycat.controller;

import org.joycat.entity.Message;
import org.joycat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {


    @Autowired
    private MessageService messageService;


    @PostMapping(value = "/msg", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Message> receiveMessage(@RequestBody Message message) {
        // вызов метода из сервиса
        System.out.println(message);
        messageService.redirectMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
