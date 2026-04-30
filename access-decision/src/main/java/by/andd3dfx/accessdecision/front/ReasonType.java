package by.andd3dfx.accessdecision.front;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reason type (layer where the grant or denial came from)")
public enum ReasonType {
    @Schema(description = "Hide or show policy based on view mode")
    VIEW_RIGHT,
    @Schema(description = "Grant-level permission reason type")
    PERMISSION,
    @Schema(description = "Runtime or business rules reason type")
    ACTION_RIGHT
}
