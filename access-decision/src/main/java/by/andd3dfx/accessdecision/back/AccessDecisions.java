package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.ReasonLayer;
import lombok.experimental.UtilityClass;

/**
 * Aggregate of factories for creating access decisions.
 * <p>
 * Use VIEW_MODE_RIGHTS, PERMISSIONS, BUSINESS_RULE_RIGHTS factories to create type-safe Access Decisions for each layer.
 */
@UtilityClass
public class AccessDecisions {

    public static final AccessDecisionFactory<ViewModeRightsAccessDecision> VIEW_MODE_RIGHTS =
            new AccessDecisionFactory<>(
                    ReasonLayer.VIEW_MODE_RIGHTS,
                    ViewModeRightsAccessDecision::new);

    public static final AccessDecisionFactory<PermissionsAccessDecision> PERMISSIONS =
            new AccessDecisionFactory<>(
                    ReasonLayer.PERMISSIONS,
                    PermissionsAccessDecision::new);

    public static final AccessDecisionFactory<BusinessRuleRightsAccessDecision> BUSINESS_RULE_RIGHTS =
            new AccessDecisionFactory<>(
                    ReasonLayer.BUSINESS_RULE_RIGHTS,
                    BusinessRuleRightsAccessDecision::new);
}
