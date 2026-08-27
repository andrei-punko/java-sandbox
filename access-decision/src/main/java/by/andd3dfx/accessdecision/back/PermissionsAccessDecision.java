package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import by.andd3dfx.accessdecision.front.ReasonLayer;

import java.util.List;

/**
 * Access Decision based on permissions (in their classic sense)
 */
public final class PermissionsAccessDecision extends AbstractAccessDecision<PermissionsAccessDecision> {

    public PermissionsAccessDecision(List<Reason> reasons) {
        super(reasons, ReasonLayer.PERMISSIONS);
    }
}
