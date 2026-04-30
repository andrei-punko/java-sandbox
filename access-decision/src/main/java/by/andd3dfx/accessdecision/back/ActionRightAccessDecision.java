package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import jakarta.annotation.Nullable;

import java.util.List;

/**
 * Access decision based on runtime or business rules.
 */
public final class ActionRightAccessDecision extends AbstractAccessDecision {

    public ActionRightAccessDecision(boolean isGranted, @Nullable List<Reason> reasons) {
        super(isGranted, reasons);
    }
}
