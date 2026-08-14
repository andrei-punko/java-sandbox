package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.ActionVisibilityState;
import by.andd3dfx.accessdecision.front.Reason;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

/**
 * Access decision verdict with ViewModeRights, Permission and BusinessRuleRights levels.
 * Suppliers used to allow making calculation in lazy way (avoid check extra layers if negative result already known).
 *
 * @param viewModeRightsSupplier     Supplier of ViewMode-level access (e.g. is program visible in this view mode)
 * @param permissionsSupplier        Supplier of Grant-level permissions (regions, roles)
 * @param businessRuleRightsSupplier Supplier of Runtime/Business rule rights (entity state, business rules)
 */
public record AccessDecisionVerdict(
        @NotNull Supplier<ViewModeRightsAccessDecision> viewModeRightsSupplier,
        @NotNull Supplier<PermissionsAccessDecision> permissionsSupplier,
        @NotNull Supplier<BusinessRuleRightsAccessDecision> businessRuleRightsSupplier
) {

    /**
     * <pre>
     * Maps AccessRightAggregate to ActionState for frontend:
     * - viewModeRights denied → INVISIBLE;
     * - permissions or businessRuleRights denied → DISABLED;
     * - all granted → ENABLED.
     *
     * Calculation performed in lazy way to avoid extra calculation in case if negative result already known.
     * </pre>
     */
    @SuppressWarnings("checkstyle:ReturnCount")
    public ActionVisibilityState toActionState() {
        ViewModeRightsAccessDecision viewModeRights = viewModeRightsSupplier.get();
        if (viewModeRights.isNotGranted()) {
            return ActionVisibilityState.invisible(collectReasons(viewModeRights));
        }

        PermissionsAccessDecision permissions = permissionsSupplier.get();
        if (permissions.isNotGranted()) {
            return ActionVisibilityState.disabled(collectReasons(viewModeRights, permissions));
        }

        BusinessRuleRightsAccessDecision businessRuleRights = businessRuleRightsSupplier.get();
        if (businessRuleRights.isNotGranted()) {
            return ActionVisibilityState.disabled(collectReasons(viewModeRights, permissions, businessRuleRights));
        }

        return ActionVisibilityState.enabled(collectReasons(viewModeRights, permissions, businessRuleRights));
    }

    private List<Reason> collectReasons(AbstractAccessDecision... accessDecisions) {
        return Arrays.stream(accessDecisions)
                .map(AbstractAccessDecision::getReasons)
                .flatMap(Collection::stream)
                // Make sorting to get determinism & wanted items order in result JSON:
                // - layer: VIEW_MODE_RIGHTS, PERMISSIONS, BUSINESS_RULE_RIGHTS
                // - type: POSITIVE, NEGATIVE
                .sorted(Comparator.comparingInt((Reason value) -> value.layer().ordinal())
                        .thenComparingInt((Reason value) -> value.type().ordinal())
                        .thenComparing(Reason::message))
                .toList();
    }
}
