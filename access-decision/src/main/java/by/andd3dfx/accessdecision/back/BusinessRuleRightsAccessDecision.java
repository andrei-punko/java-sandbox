package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import by.andd3dfx.accessdecision.front.ReasonLayer;

import java.util.List;

/**
 * Access Decision based on runtime/business rules
 */
public final class BusinessRuleRightsAccessDecision extends AbstractAccessDecision<BusinessRuleRightsAccessDecision> {

    public BusinessRuleRightsAccessDecision(List<Reason> reasons) {
        super(reasons, ReasonLayer.BUSINESS_RULE_RIGHTS);
    }
}
