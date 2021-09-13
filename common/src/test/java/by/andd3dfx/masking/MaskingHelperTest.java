package by.andd3dfx.masking;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import by.andd3dfx.masking.dto.PortfolioResponse;
import by.andd3dfx.masking.dto.WithoutAnyAnnotation;
import by.andd3dfx.masking.dto.WithoutMasked;
import by.andd3dfx.masking.dto.WithoutMaskedProperty;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class MaskingHelperTest {

    @Test
    public void mask() {
        PortfolioResponse object = PortfolioResponse.builder()
                .string("Alien")
                .maskedString("Very Important Info")
                .maskedStringWithPattern("1234567812345678")
                .stringList(Arrays.asList("one", "two", "1116788912560023"))
                .maskedStringList(Arrays.asList("one", "two", "1116788912560023"))
                .stringSet(Set.of("Axton", "Maya", "1116788912567823"))
                .maskedStringSet(Set.of("Axton", "Brick", "1216788912567823"))
                .stringToStringMap(Map.of("Bob", "1116788912560099", "Mary", "1116788912560088"))
                .maskedStringToStringMap(Map.of("Bob", "1116788912560077", "Mary", "1116788912560066"))
                .portfolioResponse(
                        PortfolioResponse.builder()
                                .string("inner str field")
                                .maskedString("inner String")
                                .maskedStringWithPattern("4556788912567823")
                                .build()
                )
                .portfolioResponseList(List.of(
                        PortfolioResponse.builder()
                                .string("portfolioResponseList str field")
                                .maskedString("portfolioResponseList String")
                                .maskedStringWithPattern("1111565689895656")
                                .build()
                ))
                .portfolioResponseSet(Set.of(
                        PortfolioResponse.builder()
                                .string("portfolioResponseSet str field")
                                .maskedString("portfolioResponseSet String")
                                .maskedStringWithPattern("2222565689895656")
                                .build()
                ))
                .stringToPortfolioResponseMap(Map.of(
                        "K1", PortfolioResponse.builder()
                                .string("K1 str field")
                                .maskedString("K1 String")
                                .maskedStringWithPattern("8989565689895656")
                                .build(),
                        "K2", PortfolioResponse.builder()
                                .string("K2 str field")
                                .maskedString("K2 another String")
                                .maskedStringWithPattern("4589785612568978")
                                .build()
                ))
                .integer(345)
                .integerList(Arrays.asList(90, 89, 88))
                .baseInt(21)
                .baseIntArray(new int[] {56, 45})
                .build();

        PortfolioResponse result = MaskingHelper.mask(object);

        checkPortfolioResponseAsserts(result, "Alien", "123456******5678");
        assertThat(result.getStringList(), is(Arrays.asList("one", "two", "1116788912560023")));
        assertThat(result.getMaskedStringList(), is(Arrays.asList("***", "***", "***")));
        assertThat(result.getStringSet(), is(Set.of("Axton", "Maya", "1116788912567823")));
        assertThat(result.getMaskedStringSet(), is(Set.of("Axton", "Brick", "121678-ZZZ-7823")));
        assertThat(result.getStringToStringMap(), is(Map.of("Bob", "1116788912560099", "Mary", "1116788912560088")));
        assertThat(result.getMaskedStringToStringMap(), is(Map.of("Bob", "111678-XYZ-0077", "Mary", "111678-XYZ-0066")));

        checkPortfolioResponseAsserts(result.getPortfolioResponse(),
                "inner str field", "455678******7823");

        checkPortfolioResponseAsserts(result.getPortfolioResponseList().get(0),
                "portfolioResponseList str field", "111156******5656");

        checkPortfolioResponseAsserts(result.getPortfolioResponseSet().iterator().next(),
                "portfolioResponseSet str field", "222256******5656");

        checkPortfolioResponseAsserts(result.getStringToPortfolioResponseMap().get("K1"),
                "K1 str field", "898956******5656");

        checkPortfolioResponseAsserts(result.getStringToPortfolioResponseMap().get("K2"),
                "K2 str field", "458978******8978");

        assertThat(result.getInteger(), is(345));
        assertThat(result.getIntegerList(), is(Arrays.asList(90, 89, 88)));
        assertThat(result.getBaseInt(), is(21));
        assertThat(result.getBaseIntArray(), is(new int[] {56, 45}));
    }

    private void checkPortfolioResponseAsserts(PortfolioResponse innerPortfolioResponse, String string, String maskedStringWithPattern) {
        assertThat(innerPortfolioResponse.getString(), is(string));
        assertThat(innerPortfolioResponse.getMaskedString(), is("***"));
        assertThat(innerPortfolioResponse.getMaskedStringWithPattern(), is(maskedStringWithPattern));
    }

    @Test
    public void maskForClassWithoutAnnotations() {
        WithoutAnyAnnotation object = WithoutAnyAnnotation.builder()
                .string("Alien")
                .build();

        WithoutAnyAnnotation result = MaskingHelper.mask(object);

        assertThat(result.getString(), is("Alien"));
    }

    @Test
    public void maskForClassWithoutMaskedAnnotation() {
        WithoutMasked object = WithoutMasked.builder()
                .string("Alien")
                .maskedString("Very Important Info")
                .build();

        WithoutMasked result = MaskingHelper.mask(object);

        assertThat(result.getString(), is("Alien"));
        assertThat(result.getMaskedString(), is("Very Important Info"));
    }

    @Test
    public void maskForClassWithoutMaskedPropertyAnnotation() {
        WithoutMaskedProperty object = WithoutMaskedProperty.builder()
                .string("Very Important Info")
                .build();

        WithoutMaskedProperty result = MaskingHelper.mask(object);

        assertThat(result.getString(), is("Very Important Info"));
    }
}
