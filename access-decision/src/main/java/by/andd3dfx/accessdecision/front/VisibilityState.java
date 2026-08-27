package by.andd3dfx.accessdecision.front;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "State of a UI control (button, menu item, etc.) as the UI should display it")
public enum VisibilityState {
    @Schema(description = "UI control for this action is visible and enabled")
    ENABLED,
    @Schema(description = "UI control for this action is visible but disabled; see reasons")
    DISABLED,
    @Schema(description = "UI control for this action is not visible")
    INVISIBLE
}
