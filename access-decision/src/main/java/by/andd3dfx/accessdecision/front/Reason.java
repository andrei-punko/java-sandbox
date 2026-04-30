package by.andd3dfx.accessdecision.front;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Grant or denial reason for an action state")
public record Reason(
        @Schema(description = "Human-readable message")
        String message,
        @Schema(description = "Reason type (source layer)")
        ReasonType type,
        @Schema(description = "Optional UI tooltip")
        String uiTooltip
) {
}
