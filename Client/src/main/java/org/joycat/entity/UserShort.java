package org.joycat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UserShort {

    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;

}
