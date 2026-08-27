package by.andd3dfx.accessdecision.front;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Reason type (positive or negative). Represent status of validation check")
public enum ReasonType {
    @Schema(description = "Positive reason type (validation passed)")
    POSITIVE,
    @Schema(description = "Negative reason type (validation failed)")
    NEGATIVE;

    public boolean isNegative() {
        return this == NEGATIVE;
    }
}
