package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import by.andd3dfx.accessdecision.front.ReasonLayer;

import java.util.List;

/**
 * Access Decision based on hide/show policy for current view mode
 */
public final class ViewModeRightsAccessDecision extends AbstractAccessDecision<ViewModeRightsAccessDecision> {

    public ViewModeRightsAccessDecision(List<Reason> reasons) {
        super(reasons, ReasonLayer.VIEW_MODE_RIGHTS);
    }
}
