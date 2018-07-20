package example.micronaut;

import io.micronaut.security.authentication.providers.UserState;

import java.util.List;

public interface UserRepository {

    UserState findByUsername(String email);

    User saveUser(String email, String password);

    List<String> findAllAuthoritiesByUsername(String email);
}
