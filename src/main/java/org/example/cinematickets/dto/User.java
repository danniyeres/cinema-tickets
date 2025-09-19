package org.example.cinematickets.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String name;
    private String email;
    private String telegram;

    public User(String name, String email, String telegram) {
        this.name = name;
        this.email = email;
        this.telegram = telegram;
    }

    public User() {
    }

}
