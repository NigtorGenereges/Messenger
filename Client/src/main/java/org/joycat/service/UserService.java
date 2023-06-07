package org.joycat.service;

import org.joycat.controller.LoginViewController;
import org.joycat.entity.User;
import org.joycat.entity.UserOnline;
import org.joycat.entity.UserShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

    private String myLogin;

    @Autowired
    private WebClient webClient;
//    @Autowired
//    private LoginViewController loginViewController;

    public void createNewUser(User user) {
        System.out.println(user);
        myLogin = user.getLogin();
        webClient
                .post()
                .uri("/user/new")
                .body(Mono.just(user), User.class)
                .retrieve()
                .bodyToMono(User.class) // TODO: change to appropriate class
                .subscribe();
    }

    public void validateUser(UserShort userShort) {
        myLogin = userShort.getLogin();
        webClient
                .post()
                .uri("/user/login")
                .body(Mono.just(userShort), User.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .subscribe(userLoggedIn -> {
                    if (userLoggedIn) {
//                        loginViewController.switchToMain();
                    }
                });
    }

    // TODO: proceed from checking list of online users (future feature)
    public void getOnlineUsers(List<String> usersOnline) {
        webClient
                .get()
                .uri("/user/online")
                .retrieve()
                .bodyToMono(List.class)
                .subscribe(usersOnline::addAll);
    }

}
