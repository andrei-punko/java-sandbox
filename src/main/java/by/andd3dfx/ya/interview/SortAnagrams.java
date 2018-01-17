package by.andd3dfx.ya.interview;

import java.util.*;

/*
We have an array:
["eat", "tea", "tan", "ate", "nat", "bat"]

We need to transform it next way:
[
  3  ["ate", "eat", "tea"],  --> sorted
  2  ["nat", "tan"],  --> sorted
  1  ["bat"]  -->  sorted
]
 */
public class SortAnagrams {
    Map<String, List<String>> voc = new HashMap<>();

    public List<List<String>> groupAnagrams(String[] items) {
        for (String item : items) {
            String key = determineKeyBySortingCharsInString(item);
            addValue(key, item);
        }

        for (String key : voc.keySet()) {
            Collections.sort(voc.get(key));
        }

        List<List<String>> result = new ArrayList<>();
        result.addAll(voc.values());
        Collections.sort(result, (List<String> a, List<String> b) -> (b.size() - a.size()));

        return result;
    }

    private String determineKeyBySortingCharsInString(String original) {
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private void addValue(String key, String value) {
        if (!voc.containsKey(key)) {
            voc.put(key, new ArrayList<>());
        }
        voc.get(key).add(value);
    }
}
