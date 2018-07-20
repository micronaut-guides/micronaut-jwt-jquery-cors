package example.micronaut;

import io.micronaut.security.authentication.providers.UserState;

import java.util.ArrayList;
import java.util.List;

public class User implements UserState {
    private String email;
    private String password;
    private boolean enabled = true;
    private boolean accountExpired = false;
    private boolean accountLocked = false;
    private boolean passwordExpired = false;
    private List<String> authorities = new ArrayList<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountExpired() {
        return accountExpired;
    }

    @Override
    public boolean isAccountLocked() {
        return accountLocked;
    }

    @Override
    public boolean isPasswordExpired() {
        return passwordExpired;
    }


}
