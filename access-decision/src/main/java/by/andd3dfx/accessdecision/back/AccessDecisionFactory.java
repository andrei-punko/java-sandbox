package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import by.andd3dfx.accessdecision.front.ReasonType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * Factory for creating access decisions. Used to create type-safe Access Decisions for each layer
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class AccessDecisionFactory<T extends AbstractAccessDecision> {

    private final String defaultDenialMessage;
    private final ReasonType reasonType;
    private final BiFunction<Boolean, List<Reason>, T> constructor;

    public T granted() {
        return constructor.apply(true, List.of());
    }

    public T granted(String message) {
        return granted(List.of(message));
    }

    public T granted(List<String> messages) {
        return constructor.apply(true, messagesToReasons(messages, reasonType));
    }

    public T denied() {
        return denied(defaultDenialMessage);
    }

    public T denied(String message) {
        return denied(List.of(message));
    }

    public T denied(List<String> messages) {
        return constructor.apply(false, messagesToReasons(messages, reasonType));
    }

    public static List<Reason> messagesToReasons(List<String> messages, ReasonType type) {
        return messages.stream()
                .filter(Objects::nonNull)
                .map(m -> new Reason(m, type, null))
                .toList();
    }
}
