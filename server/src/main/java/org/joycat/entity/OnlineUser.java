package org.joycat.entity;

import org.apache.tomcat.jni.Local;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class OnlineUser {
    @Id
    private String login;

    private LocalDateTime timeStart;
}
