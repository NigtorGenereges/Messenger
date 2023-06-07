package org.joycat.service;

import org.joycat.entity.Message;
import org.joycat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private WebClient webClient;
    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(Message message) {
        webClient
                .post()
                .uri("/msg")
                .body(Mono.just(message), Message.class)
                .retrieve()
                .bodyToMono(Message.class)
                .subscribe();
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> getDialog(String otherUserLogin) {
        return messageRepository.findAllBySenderOrRecipient(otherUserLogin, otherUserLogin);
    }

    public void deleteHistoryOfDialogWith(String otherUserLogin) {
        messageRepository.deleteAllBySenderOrRecipient(otherUserLogin, otherUserLogin);
    }

}
