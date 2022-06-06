package org.launchcode.codingevents.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginFormDTO {

    // We have a password field that will store a
    // plain-text password. However, this does not contradict
    // our early imperative about NOT storing passwords, since
    // LoginFormDTO is not a persistent class.

    @NotNull
    @NotBlank
    @Size(min = 3,max = 20,message = "Invalid username. Must be between 3 and 20 characters.")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 30, message = "Invalid password. Must be between 5 and 30 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
