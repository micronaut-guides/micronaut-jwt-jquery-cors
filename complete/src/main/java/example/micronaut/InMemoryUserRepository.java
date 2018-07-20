package example.micronaut;

import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.micronaut.security.authentication.providers.UserState;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> users = new LinkedHashMap<>();

    private final PasswordEncoder passwordEncoder;

    public InMemoryUserRepository(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserState findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public User saveUser(String email, String password) {
        final String encodedPassword = passwordEncoder.encode(password);
        users.put(email, new User(email, encodedPassword));
        return users.get(email);
    }

    @Override
    public List<String> findAllAuthoritiesByUsername(String email) {
        User user = users.get(email);
        if (user == null) {
            return new ArrayList<>();
        }
        return user.getAuthorities();
    }
}
