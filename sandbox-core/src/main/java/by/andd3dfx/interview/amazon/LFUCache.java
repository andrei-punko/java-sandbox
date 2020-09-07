package by.andd3dfx.interview.amazon;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1. put(key, value) - Set or insert the value
 * if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For
 * the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 * <p>
 * Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to
 * zero when the item is removed.
 * <p>
 * Leetcode task: https://leetcode.com/problems/lfu-cache/
 */
public class LFUCache {

    private int capacity;
    private Map<Integer, Integer> map = new HashMap<>();
    private Map<Integer, Item> freqs = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (freqs.containsKey(key)) {
            Item item = freqs.get(key);
            freqs.put(key, new Item(item.value, LocalDateTime.now(), item.hitsCount + 1));
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (freqs.containsKey(key)) {
            Item item = freqs.get(key);
            freqs.put(key, new Item(item.value, LocalDateTime.now(), item.hitsCount + 1));
        } else if (freqs.size() == capacity) {
            List<Map.Entry<Integer, Item>> entries = freqs.entrySet().stream()
                .sorted((o1, o2) -> {
                    int delta = o1.getValue().getHitsCount() - o2.getValue().getHitsCount();
                    if (delta != 0) {
                        return delta;
                    }
                    return o1.getValue().lastTimeUsed.compareTo(o2.getValue().lastTimeUsed);
                }).collect(Collectors.toList());
            Integer keyToDelete = entries.get(0).getKey();

            freqs.remove(keyToDelete);
            map.remove(keyToDelete);
            freqs.put(key, new Item(value, LocalDateTime.now(), 0));
        } else {
            freqs.put(key, new Item(value, LocalDateTime.now(), 0));
        }

        map.put(key, value);
    }

    public class Item {

        private Integer value;
        private LocalDateTime lastTimeUsed;
        private int hitsCount = 0;

        public Item(Integer value, LocalDateTime lastTimeUsed, int hitsCount) {
            this.value = value;
            this.lastTimeUsed = lastTimeUsed;
            this.hitsCount = hitsCount;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public LocalDateTime getLastTimeUsed() {
            return lastTimeUsed;
        }

        public void setLastTimeUsed(LocalDateTime lastTimeUsed) {
            this.lastTimeUsed = lastTimeUsed;
        }

        public int getHitsCount() {
            return hitsCount;
        }

        public void setHitsCount(int hitsCount) {
            this.hitsCount = hitsCount;
        }
    }
}
