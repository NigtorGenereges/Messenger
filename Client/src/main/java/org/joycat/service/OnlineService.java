package org.joycat.service;

import org.joycat.entity.UserOnline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OnlineService {

    private String myLogin = "storror";

    @Autowired
    private WebClient webClient;

    public void turnOnline() {
        UserOnline userOnline = new UserOnline();
        userOnline.setLogin(myLogin);
        webClient
                .post()
                .uri("/user/online")
                .body(Mono.just(userOnline), UserOnline.class)
                .retrieve()
                .bodyToMono(UserOnline.class)
                .subscribe();
    }

}
