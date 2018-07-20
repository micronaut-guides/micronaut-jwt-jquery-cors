package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.Secured;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@Controller("/user")
public class UserController {

    @Secured("isAuthenticated()")
    @Get("/gravatar")
    HttpResponse<Map> gravatar(Principal principal) {
        String email = principal.getName().trim().toLowerCase();
        String hash = MD5Util.md5Hex(email);
        if (hash == null) {
            return HttpResponse.serverError();
        }
        return HttpResponse.ok(Collections.singletonMap("hash", hash));
    }
}
