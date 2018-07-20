package example.micronaut;

import io.micronaut.security.authentication.providers.AuthoritiesFetcher;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class AuthoritiesFetcherService implements AuthoritiesFetcher {

    protected final UserRepository userRepository;

    public AuthoritiesFetcherService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Publisher<List<String>> findAuthoritiesByUsername(String username) {
        return Flowable.just(userRepository.findAllAuthoritiesByUsername(username));
    }
}