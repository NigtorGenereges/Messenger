package org.joycat.service;

import org.joycat.entity.UserOnline;
import org.joycat.repository.UserOnlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserOnlineService {

    @Autowired
    private UserOnlineRepository userOnlineRepository;

    public boolean isOnline(String login) {
        return userOnlineRepository.findByLogin(login).isPresent();
    }

    public UserOnline findUserOnline(String login) {
        return userOnlineRepository.findByLogin(login).orElse(null);
    }

    public void turnOnline(final UserOnline userOnline) {
        System.out.println("### USER ONLINE: " + userOnline.getLogin());
        userOnline.setIp("localhost");
        userOnline.setTimeStart(LocalDateTime.now());
        userOnlineRepository.save(userOnline);
    }

    public void turnOffline(UserOnline userOnline) {
        userOnlineRepository.deleteByLogin(userOnline.getLogin());
    }

}

