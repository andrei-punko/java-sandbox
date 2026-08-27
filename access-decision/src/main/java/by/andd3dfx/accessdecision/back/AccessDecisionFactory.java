package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import by.andd3dfx.accessdecision.front.ReasonLayer;
import by.andd3dfx.accessdecision.front.ReasonType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Factory for creating access decisions. Used to create type-safe Access Decisions for each layer
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public final class AccessDecisionFactory<T extends AbstractAccessDecision<T>> {

    private final ReasonLayer reasonLayer;
    private final Function<List<Reason>, T> accessDecisionConstructor;

    public T create() {
        return accessDecisionConstructor.apply(new ArrayList<>());
    }

    public T granted(String... messages) {
        return accessDecisionConstructor.apply(messagesToReasons(ReasonType.POSITIVE, messages));
    }

    public T denied(String... messages) {
        return accessDecisionConstructor.apply(messagesToReasons(ReasonType.NEGATIVE, messages));
    }

    private List<Reason> messagesToReasons(ReasonType reasonType, String... messages) {
        return Arrays.stream(messages)
                .filter(Objects::nonNull)
                .map(msg -> new Reason(reasonLayer, reasonType, msg))
                .toList();
    }
}
