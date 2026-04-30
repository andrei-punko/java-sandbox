package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.ReasonType;
import lombok.experimental.UtilityClass;

/**
 * Shared factories for view, permission, and action-right decisions.
 */
@UtilityClass
public class AccessDecisions {

    public static final AccessDecisionFactory<ViewRightAccessDecision> VIEW_RIGHT =
            new AccessDecisionFactory<>(
                    "Not allowed for current view",
                    ReasonType.VIEW_RIGHT,
                    ViewRightAccessDecision::new);

    public static final AccessDecisionFactory<PermissionAccessDecision> PERMISSION =
            new AccessDecisionFactory<>(
                    "Additional permissions needed",
                    ReasonType.PERMISSION,
                    PermissionAccessDecision::new);

    public static final AccessDecisionFactory<ActionRightAccessDecision> ACTION_RIGHT =
            new AccessDecisionFactory<>(
                    "Action is not allowed from a business perspective",
                    ReasonType.ACTION_RIGHT,
                    ActionRightAccessDecision::new);
}
