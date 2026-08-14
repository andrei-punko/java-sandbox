package by.andd3dfx.accessdecision.front;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reason layer where the grant/denial came from")
public enum ReasonLayer {
    @Schema(description = "Hide/show policy (based on view mode) reason layer")
    VIEW_MODE_RIGHTS,
    @Schema(description = "Grant-level (classic permissions) reason layer")
    PERMISSIONS,
    @Schema(description = "Runtime/business rules reason layer")
    BUSINESS_RULE_RIGHTS
}
