package org.joycat.repository;

import org.joycat.entity.Message;
import org.joycat.entity.OnlineUser;
import org.joycat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OnlineUserRepository extends JpaRepository<OnlineUser, Integer> {
    Optional<OnlineUser> findByLogin(String login);


}

