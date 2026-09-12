package com.connectSphere.APIGateway.filter;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Custom authentication filter for the Gateway
 */
@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService) {

        // Tells the parent class that Config is used for this filter.
        super(Config.class);

        this.jwtService = jwtService;
    }

    // Creates and returns the actual Gateway filter.
    @Override
    public GatewayFilter apply(Config config) {

        // `exchange` = current request/response.
        // `chain` = remaining filters.
        return (exchange, chain) -> {

            log.info("Auth request: {}", exchange.getRequest().getURI());

            final String tokenHeader = exchange.getRequest()
                                               .getHeaders()
                                               .getFirst("Authorization");

            if (tokenHeader == null || !tokenHeader.startsWith("Bearer")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

                return exchange.getResponse().setComplete();
            }

            try {
                final var token = tokenHeader.split("Bearer ")[1];

                // Validates the JWT and gets the user ID.
                String userId = jwtService.getUserIdFromToken(token);

                // Creates a modified request.
                final var mutatedExchange = exchange.mutate()
                                                    // Adds the authenticated user's ID
                                                    // to the request header.
                                                    //
                                                    // X-User-Id: 123
                                                    .request(r ->
                                                                 r.header("X-User-Id", userId))
                                                    // Creates the modified exchange.
                                                    .build();

                // Authentication succeeded.
                // Forward the request to the next filter/service.
                return chain.filter(mutatedExchange);

            }
            catch (JwtException e) {
                log.error("JWT Exception {}",  e.getLocalizedMessage());

                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

                return exchange.getResponse().setComplete();
            }
        };
    }

    // Holds configuration for this filter.
    // Currently there are no settings.
    static class Config {}
}
