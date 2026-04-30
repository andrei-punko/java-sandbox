package by.andd3dfx.accessdecision.back;

import by.andd3dfx.accessdecision.front.Reason;
import jakarta.annotation.Nullable;

import java.util.List;

/**
 * Access decision based on visibility policy for the current view mode.
 */
public final class ViewRightAccessDecision extends AbstractAccessDecision {

    public ViewRightAccessDecision(boolean isGranted, @Nullable List<Reason> reasons) {
        super(isGranted, reasons);
    }
}
