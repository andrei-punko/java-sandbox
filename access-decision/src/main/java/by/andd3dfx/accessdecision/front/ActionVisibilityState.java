package by.andd3dfx.accessdecision.front;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@SuppressFBWarnings(value = {"EI_EXPOSE_REP"}, justification = "DTO for frontend, data is serialized out")
@Schema(description = "Action visibility state with reasons for frontend")
public record ActionVisibilityState(
        @Schema(description = "Visibility state: ENABLED / DISABLED / INVISIBLE")
        VisibilityState state,
        @Schema(description = "Grant/denial reasons of state. Reasons sorted next way: [ViewMode, Permissions, BusinessRule], " +
                "and inside each category - by type, message", nullable = true)
        List<Reason> reasons
) {
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
