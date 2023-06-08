package org.joycat.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@NoArgsConstructor  // Boilerplate
@ToString
// TODO: validation is not active (@Valid or @Validated?)
public class User {

    @Getter
    @Setter
    @Length(min = 3)
    private String login;
    @Getter
    @Setter
    @Length(min = 2)
    private String name;
    @Getter
    @Setter
    @Length(min = 8)
    private String password;
    @Getter
    @Setter
    @Length(min = 5)
    @Pattern(regexp = "[\\w\\d]+@\\w+\\.\\w+") // example34@mymail.com
    private String email;
    @Getter
    @Setter
    @Pattern(regexp = "8([-\\s(]?)*\\d{3}([-\\s)]?)*\\d{3}([-\\s]?\\d{2}){2}") // 89997775533 or 8(999)777-55-33 or 8-(999)-777-55-33 or 8(999) 777-55-33 etc.
    private String phone;


}
