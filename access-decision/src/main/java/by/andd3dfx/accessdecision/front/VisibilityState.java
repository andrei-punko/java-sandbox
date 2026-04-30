package by.andd3dfx.accessdecision.front;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Visibility state for UI actions")
public enum VisibilityState {
    @Schema(description = "Action is visible and enabled")
    ENABLED,
    @Schema(description = "Action is visible but disabled")
    DISABLED,
    @Schema(description = "Action is not visible")
    INVISIBLE
}
