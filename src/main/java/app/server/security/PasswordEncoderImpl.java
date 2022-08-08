package app.server.security;

import com.google.common.hash.Hashing;
import jakarta.inject.Singleton;

import java.nio.charset.StandardCharsets;

@Singleton
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(String rawPassword) {
        return Hashing.sha256().hashString(rawPassword, StandardCharsets.UTF_8).toString();
    }
    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}