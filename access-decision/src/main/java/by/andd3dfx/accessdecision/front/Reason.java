package by.andd3dfx.accessdecision.front;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Action state grant/denial reason")
public record Reason(
        @Schema(description = "Reason layer where the grant/denial came from")
        ReasonLayer layer,
        @Schema(description = "Reason type (positive or negative)")
        ReasonType type,
        @Schema(description = "Human-readable message (can be used directly or as fallback if FE localizes by code)")
        String message
) {
    @JsonIgnore
    public boolean isNegative() {
        return type.isNegative();
    }
}
