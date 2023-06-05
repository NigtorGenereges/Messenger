package org.joycat.service;

import org.joycat.entity.Message;
import org.joycat.entity.OnlineUser;
import org.joycat.entity.User;
import org.joycat.repository.MessageRepository;
import org.joycat.repository.OnlineUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private OnlineUserRepository onlineUserRepository;

//    @Autowired
//    private WebClient webClient;

    public void saveMessage(Message message) {
        message.setSendTime(LocalDateTime.now());
        messageRepository.save(message);
    }
//TODO: 1) адрес стринга в олнайн юзере
//TODO: 2) в редиректе сендж месседж
    public void sendMessage(Message message, String address) {

//        webClient.post()
//                .uri(address + ":4077")
//                .retrieve()
//                .bodyToMono(Message.class)
//                .block();


    }

    public void redirectMessage(Message message) {

        OnlineUser onlineUser = onlineUserRepository.findByLogin(message.getRecipient()).orElse(null);

        try {
            sendMessage(message, onlineUser.getIp());
        } catch (NullPointerException e) {
            messageRepository.save(message);
        }

    }
}

