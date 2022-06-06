package org.launchcode.codingevents.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity{

    // You should never store passwords in a database.
    // authentication is done by password hashing, an encryption.
    // The password itself is not actually stored but the pw_hash
    // associated with the user id will

    // The org.springframework.security:spring-security-crypto
    // dependency provides BCryptPasswordEncoder class which
    // will be used to create and verify hashes
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String username;
    @NotNull
    private String pwHash;

    public User(){}

    public User(String username, String passWord){
        this.username = username;
        this.pwHash = encoder.encode(passWord);
    }


    // plain equals will not work since the pw is stored in DB as a hash.
    // Plus, there are extra encryption done on the pw, such as salting
    // to make pw more robust.
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public String getUserName() {
        return username;
    }
}
