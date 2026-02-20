package lrucache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final DoublyLinkedList<K, V> list;
    private final Lock lock;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DoublyLinkedList<>();
        this.lock = new ReentrantLock();
    }

    public V get(K key) {
        lock.lock();
        try {
            Node<K, V> node = map.get(key);
            if (node == null) {
                return null;
            }
            list.moveToHead(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    public void put(K key, V value) {
        lock.lock();
        try {
            Node<K, V> node = map.get(key);
            if (node != null) {
                node.value = value;
                list.moveToHead(node);
                return;
            }
            if (map.size() >= capacity) {
                Node<K, V> evicted = list.removeTail();
                if (evicted != null) {
                    map.remove(evicted.key);
                }
            }
            Node<K, V> newNode = new Node<>(key, value);
            map.put(key, newNode);
            list.addToHead(newNode);
        } finally {
            lock.unlock();
        }
    }
}
