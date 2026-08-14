package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import by.andd3dfx.accessdecision.front.ReasonLayer;

import java.util.List;

/**
 * Access Decision based on hide/show policy for current view mode
 */
public final class ViewModeRightsAccessDecision extends AbstractAccessDecision {

    public ViewModeRightsAccessDecision(List<Reason> reasons) {
        super(reasons, ReasonLayer.VIEW_MODE_RIGHTS);
    }

    public ViewModeRightsAccessDecision addReason(boolean conditionFlag, String universalReasonMessage) {
        return addReason(conditionFlag, universalReasonMessage, universalReasonMessage);
    }

    public ViewModeRightsAccessDecision addReason(boolean conditionFlag, String positiveReasonMessage, String negativeReasonMessage) {
        if (conditionFlag) {
            addGrant(positiveReasonMessage);
        } else {
            addDeny(negativeReasonMessage);
        }
        return this;
    }
}
