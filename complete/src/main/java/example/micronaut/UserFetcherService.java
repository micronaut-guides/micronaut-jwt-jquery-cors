package example.micronaut;

import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import javax.inject.Singleton;

@Singleton
public class UserFetcherService implements UserFetcher {

    protected final UserRepository userRepository;

    public UserFetcherService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Publisher<UserState> findByUsername(String username) {
        UserState user = userRepository.findByUsername(username);
        return user != null ? Flowable.just(user) : Flowable.empty();
    }
}