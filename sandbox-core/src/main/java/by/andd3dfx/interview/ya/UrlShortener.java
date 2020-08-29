package by.andd3dfx.interview.ya;

import java.util.HashMap;
import java.util.Map;

/**
 * According to https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener/742047
 *
 * - Написать сократитель ссылок
 * 1. записываем строку из символов англ. алфавита и цифр: abc..zABC..Z01..9 (62 символа)
 * 2. записываем исходную ссылку в БД, кодируем полученную ID записи по основанию 62 в цифро-буквенную строку
 * 3. записываем в БД (в ту же строку) сгенерированную цифро-буквенную строку
 * 4. возвращаем юзеру сгенерированную цифро-буквенную строку
 *
 * - Описать реализацию шардирования сократителя ссылок
 * = Кодирование длинного урла (1й вариант):
 * 1. из исходного url отбрасываем 'http://' вначале, берем 1+ первых букв (сколько - зависит от кол-ва шард N у нас), кодируем эту пару букв в число (с основанием 26), берем остаток от деления числа этого на N - это будет номер шарды
 * 2. добавляем в таблицу БД новую запись вида (ID, LONG_STRING), генерируем короткую строку SHORT_STRING по ID, дописываем SHORT_STRING в эту строку.
 * 3. возвращаем пользователю строку вида 'http://' + домен кодировщика урлов + '/' + (использованые первые буквы исх. урла) + SHORT_STRING
 *
 * = Декодирование короткого урла (1й вариант):
 * 1. достаем нужное кол-во первых букв, декодируем в номер шарды
 * 2. из данной шарды достаем данные по ключу, полученного декодированием остальной строки
 * 3. возвращаем юзеру LONG_STRING
 *
 * = Кодирование url-a (2й вариант):
 * 1. выбираем случайную шарду. конвертируем номер шарды в букву (либо несколько букв, если шард много). так получим строку SHARD_STRING
 * 2. см. п.2 выше
 * 3. возвращаем пользователю строку вида 'http://' + домен кодировщика урлов + '/' + SHARD_STRING + SHORT_STRING
 *
 * = Декодирование короткого урла (2й вариант):
 * совпадает с первым вариантом
 */
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
