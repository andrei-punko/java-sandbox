package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;

import java.util.List;

/**
 * Access decision based on classic permission grants.
 */
public final class PermissionAccessDecision extends AbstractAccessDecision {

    public PermissionAccessDecision(boolean isGranted, List<Reason> reasons) {
        super(isGranted, reasons);
    }
}
