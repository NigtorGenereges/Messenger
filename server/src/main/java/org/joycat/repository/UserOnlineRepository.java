package org.joycat.repository;

import org.joycat.entity.UserOnline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOnlineRepository extends JpaRepository<UserOnline, Integer> {
    Optional<UserOnline> findByLogin(String login);

    void deleteByLogin(String login);

    boolean existsByLogin(String login);

}

