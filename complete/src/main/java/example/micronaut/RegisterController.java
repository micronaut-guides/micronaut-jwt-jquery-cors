package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Options;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.Secured;
import io.micronaut.security.authentication.providers.UserState;
import io.micronaut.validation.Validated;

import javax.validation.Valid;

@Validated
@Controller("/register")
public class RegisterController {

    private final UserRepository userRepository;

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Secured("isAnonymous()")
    @Post("/")
    HttpResponse register(@Valid @Body RegisterCommand cmd) {
        UserState user = userRepository.findByUsername(cmd.username);
        if (user != null) {
            return HttpResponse.badRequest();
        }
        userRepository.saveUser(cmd.getUsername(), cmd.getPassword());
        return HttpResponse.ok();
    }
}
