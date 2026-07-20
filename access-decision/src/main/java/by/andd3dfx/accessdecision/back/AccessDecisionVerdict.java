package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.ActionVisibilityState;
import by.andd3dfx.accessdecision.front.Reason;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Access decision verdict with view, permission and action levels
 */
public record AccessDecisionVerdict(
        /*
          View-level access (e.g. program visible in view)
         */
        @NotNull ViewRightAccessDecision viewRight,
        /*
          Grant-level permission (regions, roles)
         */
        @NotNull PermissionAccessDecision permission,
        /*
          Runtime/business action right (entity state, business rules)
         */
        @NotNull ActionRightAccessDecision actionRight
) {

    /**
     * Maps this aggregate to a UI action state:
     * view denied → INVISIBLE; permission or action denied → DISABLED; all granted → ENABLED.
     */
    public ActionVisibilityState toActionState() {
        if (viewRight.isNotGranted()) {
            return ActionVisibilityState.invisible(collectNegativeReasons(viewRight));
        }

        if (permission.isNotGranted() || actionRight.isNotGranted()) {
            return ActionVisibilityState.disabled(collectNegativeReasons(permission, actionRight));
        }

        return ActionVisibilityState.enabled(collectPositiveReasons(viewRight, permission, actionRight));
    }

    private List<Reason> collectNegativeReasons(AbstractAccessDecision... accessDecisions) {
        return collectReasons(AbstractAccessDecision::isNotGranted, accessDecisions);
    }

    private List<Reason> collectPositiveReasons(AbstractAccessDecision... accessDecisions) {
        return collectReasons(AbstractAccessDecision::isGranted, accessDecisions);
    }

    private List<Reason> collectReasons(Predicate<? super AbstractAccessDecision> predicate,
                                        AbstractAccessDecision... accessDecisions) {
        return Arrays.stream(accessDecisions)
                .filter(predicate)
                .map(AbstractAccessDecision::getReasons)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Reason::type).thenComparing(Reason::message))
                .toList();
    }

    public boolean isGranted() {
        return viewRight.isGranted() && permission.isGranted() && actionRight.isGranted();
    }
}
