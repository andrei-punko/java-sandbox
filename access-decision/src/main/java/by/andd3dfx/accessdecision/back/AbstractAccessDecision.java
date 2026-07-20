package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public abstract class AbstractAccessDecision {

    private final boolean isGranted;
    private final List<Reason> reasons;

    protected AbstractAccessDecision(boolean isGranted, List<Reason> reasons) {
        this.isGranted = isGranted;
        this.reasons = reasons;
    }

    public boolean isNotGranted() {
        return !isGranted;
    }

    public String getExceptionMessage() {
        if (reasons == null) {
            throw new IllegalStateException("No reasons present!");
        }
        return reasons.stream()
                .map(Reason::message)
                .collect(Collectors.joining(","));
    }
}
