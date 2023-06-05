package org.joycat.service;

import org.joycat.entity.OnlineUser;
import org.joycat.repository.OnlineUserRepository;
import org.joycat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineUserService {


    @Autowired
    private OnlineUserRepository onlineUserRepository;


    public boolean isOnline(String login) {
        return onlineUserRepository.findByLogin(login).isPresent();
    }


    public OnlineUser findUserOnline(String login) {
        return onlineUserRepository.findByLogin(login).orElse(null);
    }

}

//    public  void saveOnlinUser(Str)
//
//        public deleteOnline()
//
//}
