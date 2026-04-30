package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.ActionVisibilityState;
import by.andd3dfx.accessdecision.front.Reason;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Aggregate of view, permission, and action-right decisions.
 */
public record AccessDecisionAggregate(
        @NotNull ViewRightAccessDecision viewRight,
        @NotNull PermissionAccessDecision permission,
        @NotNull ActionRightAccessDecision actionRight
) {

    /**
     * Maps this aggregate to a UI action state:
     * view denied → INVISIBLE; permission or action denied → DISABLED; all granted → ENABLED.
     */
    public ActionVisibilityState toActionState() {
        if (viewRight.isNotGranted()) {
            return ActionVisibilityState.invisible(collectReasons(viewRight));
        }

        if (permission.isNotGranted() || actionRight.isNotGranted()) {
            return ActionVisibilityState.disabled(collectReasons(permission, actionRight));
        }

        return ActionVisibilityState.enabled(collectReasons(viewRight, permission, actionRight));
    }

    private List<Reason> collectReasons(AbstractAccessDecision... accessDecisions) {
        return Arrays.stream(accessDecisions)
                .map(AbstractAccessDecision::getReasons)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Reason::type).thenComparing(Reason::message))
                .toList();
    }

    public boolean isGranted() {
        return viewRight.isGranted() && permission.isGranted() && actionRight.isGranted();
    }
}
