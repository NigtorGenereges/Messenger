package org.joycat.service;

import org.joycat.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    @Autowired
    private WebClient webClient;

    public void sendMessage(Message message) {
        webClient
                .post()
                .uri("/msg")
                .body(Mono.just(message), Message.class)
                .retrieve()
                .bodyToMono(Message.class)
                .subscribe();
    }

}
