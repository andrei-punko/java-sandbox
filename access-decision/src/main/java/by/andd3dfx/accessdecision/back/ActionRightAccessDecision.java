package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;

import java.util.List;

/**
 * Access decision based on runtime or business rules.
 */
public final class ActionRightAccessDecision extends AbstractAccessDecision {

    public ActionRightAccessDecision(boolean isGranted, List<Reason> reasons) {
        super(isGranted, reasons);
    }
}
