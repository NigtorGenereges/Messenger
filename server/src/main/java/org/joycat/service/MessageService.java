package org.joycat.service;

import io.reactivex.rxjava3.core.Observable;
import org.joycat.entity.Message;
import org.joycat.entity.UserOnline;
import org.joycat.repository.MessageRepository;
import org.joycat.repository.UserOnlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserOnlineRepository userOnlineRepository;

//    @Value("client.port")
    private final String clientPort = "9677";

//    @Autowired
//    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    public void saveMessage(Message message) {
        message.setSendTime(LocalDateTime.now());
        messageRepository.save(message);
    }
//TODO: 1) адрес стринга в олнайн юзере
//TODO: 2) в редиректе сендж месседж
    public void sendMessage(Message message, String address) {

        Observable.just(message).subscribe(msg -> {
            HttpEntity<Message> request = new HttpEntity<>(msg);
            ResponseEntity<Message> messageResponseEntity = restTemplate.postForEntity("http://" + address + ":" + clientPort + "/msg", request, Message.class);
            if (messageResponseEntity.getStatusCode() != HttpStatus.OK) {
                recacheMessage(msg);
            }
        });


    }

    // If user connection is interrupted
    public void recacheMessage(Message message) {
        messageRepository.save(message);
    }

    public void redirectMessage(Message message) {

        UserOnline userOnline = userOnlineRepository.findByLogin(message.getRecipient()).orElse(null);

        try {
            sendMessage(message, userOnline.getIp());
        } catch (NullPointerException e) {
            messageRepository.save(message);
        }

    }
}

