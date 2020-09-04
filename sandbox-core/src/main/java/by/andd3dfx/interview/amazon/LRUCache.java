package by.andd3dfx.interview.amazon;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Leetcode task: https://leetcode.com/problems/lru-cache/
 */
public class LRUCache {

    private int capacity;
    private Map<Integer, Integer> map = new HashMap<>();
    private Set<Integer> set = new LinkedHashSet<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (set.contains(key)) {
            set.remove(key);
            set.add(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (set.contains(key)) {
            set.remove(key);
        } else if (set.size() == capacity) {
            Integer keyToDelete = (Integer) set.toArray()[0];
            set.remove(keyToDelete);
            map.remove(keyToDelete);
        }
        set.add(key);

        map.put(key, value);
    }
}
