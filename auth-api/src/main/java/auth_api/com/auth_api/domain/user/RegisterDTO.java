package auth_api.com.auth_api.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
