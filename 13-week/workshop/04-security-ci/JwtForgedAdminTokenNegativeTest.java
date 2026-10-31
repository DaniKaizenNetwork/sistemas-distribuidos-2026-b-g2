package co.opticacentral.optiview.infrastructure.driven_adapters.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Negative authorization test for opti-docs issue #48 (STRIDE E, risk 6, P0).
 * An ADMIN token signed with any key other than the configured app.jwt.secret
 * must not authenticate, so it cannot reach POST /api/frames (ADMIN only).
 */
class JwtForgedAdminTokenNegativeTest {

    private static final String CONFIGURED = "deployment-specific-secret-with-more-than-32-bytes";
    private static final String ATTACKER_KEY = "clave-de-desarrollo-optiview-cambiar-min-32-bytes";

    private final JwtTokenProvider provider = new JwtTokenProvider(CONFIGURED, 480);

    @Test
    void admin_token_signed_with_another_key_is_rejected() {
        String forged = Jwts.builder()
                .subject("attacker")
                .claim("role", "ADMIN")
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusSeconds(600)))
                .signWith(Keys.hmacShaKeyFor(ATTACKER_KEY.getBytes(StandardCharsets.UTF_8)))
                .compact();

        assertThat(provider.verify(forged)).isEmpty();
    }

    @Test
    void secret_shorter_than_32_bytes_is_refused_at_startup() {
        assertThatThrownBy(() -> new JwtTokenProvider("short-secret", 480))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("at least 32 bytes");
    }
}
