package example.micronaut;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.rules.SecurityRuleResult;
import io.micronaut.web.router.RouteMatch;
import javax.annotation.Nullable;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Optional;

/**
 * A {@link SecurityRule} to authorize CORS PreFlight Requests
 *
 * @author Sergio del Amo
 * @since 1.0
 */
@Requires(property = "micronaut.server.cors.enabled", value = "true")
@Singleton
public class PreflightRequestRule implements SecurityRule {

    @Override
    public SecurityRuleResult check(HttpRequest request, @Nullable RouteMatch routeMatch, @Nullable Map<String, Object> claims) {

        Optional<String> origin = request.getHeaders().getOrigin();
        Optional<String> accessControlRequestMethod = request.getHeaders().findFirst(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD);
        Optional<String> accessControlRequestHeaders = request.getHeaders().findFirst(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS);

        if ( origin.isPresent() &&
                accessControlRequestHeaders.isPresent() &&
                accessControlRequestMethod.isPresent() &&
                request.getMethod().equals(HttpMethod.OPTIONS)
                ) {
            return SecurityRuleResult.ALLOWED;
        }

        return SecurityRuleResult.UNKNOWN;
    }
}
