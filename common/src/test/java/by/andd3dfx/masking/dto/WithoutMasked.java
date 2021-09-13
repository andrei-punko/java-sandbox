package by.andd3dfx.masking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import by.andd3dfx.masking.MaskedProperty;

@Builder
@AllArgsConstructor
@Data
public class WithoutMasked {

    private String string;

    @MaskedProperty
    private String maskedString;
}
