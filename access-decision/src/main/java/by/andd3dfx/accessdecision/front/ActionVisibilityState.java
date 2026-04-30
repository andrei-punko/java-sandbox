package by.andd3dfx.accessdecision.front;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "DTO serialized to clients")
@Schema(description = "Action visibility with optional reasons for the UI")
public record ActionVisibilityState(
        @Schema(description = "ENABLED, DISABLED, or INVISIBLE")
        VisibilityState state,
        @Schema(description = "Reasons for the current state", nullable = true)
        List<Reason> reasons
) {
    public static ActionVisibilityState enabled() {
        return enabled(null);
    }

    public static ActionVisibilityState enabled(List<Reason> reasons) {
        return new ActionVisibilityState(VisibilityState.ENABLED, reasons);
    }

    public static ActionVisibilityState disabled(List<Reason> reasons) {
        return new ActionVisibilityState(VisibilityState.DISABLED, reasons);
    }

    public static ActionVisibilityState invisible(List<Reason> reasons) {
        return new ActionVisibilityState(VisibilityState.INVISIBLE, reasons);
    }
}
