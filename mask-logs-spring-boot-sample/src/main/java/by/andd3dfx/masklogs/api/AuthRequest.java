package by.andd3dfx.masklogs.api;

public record AuthRequest(
        String username,
        String password,
        String cardNumber
) {
}
