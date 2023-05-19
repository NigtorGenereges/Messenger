package org.joycat.service;

import org.joycat.entity.Message;
import org.joycat.entity.User;
import org.joycat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    //TODO: 1)Воссоздать структуру сохранения сообщений и дял юзеров. НАстроить h2 с пропертями.

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private OnlineUserService onlineUserService;

    public void saveMessage(Message message) {
        message.setSendTime(LocalDateTime.now());
        messageRepository.save(message);
    }
//TODO: 1) адрес стринга в олнайн юзере
//TODO: 2) в редиректе сендж месседж
    public void sendMessage(Message message, String address) {



    }

    public void redirectMessage(Message message) {
        if (onlineUserService.isOnline(message.getRecipient())) {
            sendMessage(message);
        } else {
            messageRepository.save(message);
        }


    }
}

