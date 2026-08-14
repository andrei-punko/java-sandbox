package by.andd3dfx.accessdecision.front;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reason type (positive or negative)")
public enum ReasonType {
    @Schema(description = "Positive reason")
    POSITIVE,
    @Schema(description = "Negative reason")
    NEGATIVE;

    public boolean isNegative() {
        return this == NEGATIVE;
    }
}
