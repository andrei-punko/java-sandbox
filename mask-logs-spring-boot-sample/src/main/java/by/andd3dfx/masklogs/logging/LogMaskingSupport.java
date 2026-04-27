package by.andd3dfx.masklogs.logging;

import java.util.List;
import java.util.regex.Pattern;

public final class LogMaskingSupport {

    private static final String MASK = "***";

    private static final List<MaskRule> RULES = List.of(
            new MaskRule(
                    Pattern.compile("(?i)(\"?(?:password|pass|pwd|secret|token|accessToken|refreshToken|authorization)\"?\\s*[:=]\\s*\")([^\"]+)(\")"),
                    "$1" + MASK + "$3"
            ),
            new MaskRule(
                    Pattern.compile("(?i)(\\b(?:password|pass|pwd|secret|token|accessToken|refreshToken|authorization)\\b\\s*[:=]\\s*)([^,}\\]]+)"),
                    "$1" + MASK
            ),
            new MaskRule(
                    Pattern.compile("(?i)(\\bBearer\\s+)([A-Za-z0-9._\\-]+)"),
                    "$1" + MASK
            ),
            new MaskRule(
                    Pattern.compile("\\b\\d{12,19}\\b"),
                    "************"
            )
    );

    private LogMaskingSupport() {
    }

    public static String mask(String source) {
        if (source == null || source.isEmpty()) {
            return source;
        }

        String masked = source;
        for (MaskRule rule : RULES) {
            masked = rule.pattern().matcher(masked).replaceAll(rule.replacement());
        }
        return masked;
    }

    private record MaskRule(Pattern pattern, String replacement) {
    }
}
