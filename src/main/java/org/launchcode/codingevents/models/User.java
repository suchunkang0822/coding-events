package org.launchcode.codingevents.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String userName;
    @NotNull
    private String pwHash;

    public User(){}

    public User(String userName, String passWord){
        this.userName = userName;
        this.pwHash = encoder.encode(passWord);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public String getUserName() {
        return userName;
    }
}
