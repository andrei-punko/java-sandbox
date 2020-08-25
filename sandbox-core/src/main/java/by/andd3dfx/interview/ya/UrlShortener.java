package by.andd3dfx.interview.ya;

import java.util.HashMap;
import java.util.Map;

public class UrlShortener {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    private static Long dbPrimaryKey = 0L;
    private static final Map<Long, String> pkToLongStringMap = new HashMap<>();
    private static final Map<String, String> longStringToShortStringMap = new HashMap<>();

    public static String buildShortUrl(String longString) {
        if (longStringToShortStringMap.containsKey(longString)) {
            return longStringToShortStringMap.get(longString);
        }

        dbPrimaryKey++;
        pkToLongStringMap.put(dbPrimaryKey, longString);

        String shortString = encodePrimaryKeyToShortString(dbPrimaryKey);
        longStringToShortStringMap.put(longString, shortString);
        return shortString;
    }

    public static String restoreLongUrl(String shortString) {
        Long primaryKey = decodeShortStringToPrimaryKey(shortString);
        return pkToLongStringMap.get(primaryKey);
    }

    static String encodePrimaryKeyToShortString(Long dbPrimaryKey) {
        StringBuilder sb = new StringBuilder();
        while (dbPrimaryKey > 0) {
            Long remainder = dbPrimaryKey % BASE;
            sb.append(ALPHABET.charAt(remainder.intValue()));
            dbPrimaryKey = dbPrimaryKey / BASE;
        }
        return sb.reverse().toString();
    }

    static Long decodeShortStringToPrimaryKey(String shortString) {
        Long result = 0L;
        for (char character : shortString.toCharArray()) {
            result *= BASE;
            int charIndex = ALPHABET.indexOf(character);
            result += charIndex;
        }
        return result;
    }
}
