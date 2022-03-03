package com.jdriven.jdkworkshop.exercises.streamoptional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Basic cache implementation (not thread safe!) consisting of 2 levels: an in-memory cache and a disk cache.
 * If an entry is put into the cache, the memory cache is used first. If the memory cache is full,
 * the oldest entry is drained to disk.
 */
public class Cache<K, V> {

    private final CacheImplementation<K, V> memoryCache = new CacheImplementation<>(3);
    private final CacheImplementation<K, V> diskCache = new CacheImplementation<>(100);

    /**
     * Implement this find method using the {@link Optional#or(Supplier)}. The method should first attempt to
     * find a cached entry in the memoryCache. If no entry is found, the diskCache should be queried. If there
     * is still no entry, the method should return an empty Optional.
     */
    public Optional<V> find(K key) {
        return memoryCache.find(key).or(() -> diskCache.find(key));
    }

    /**
     * Implement this find method using {@link Optional#stream()}. Given a variable amount of keys, this method
     * should return a stream of values belonging to these keys, if present.
     *
     * TIP: The method {@link Arrays#stream(Object[])} can be used to create a Stream from an array,
     *      and {@link Stream#flatMap(Function)} can be used to flatten a Stream of Streams.
     */
    public Stream<V> find(K... keys) {
        return Arrays.stream(keys)
                .map(this::find)
                .flatMap(Optional::stream);
    }

    /**
     * Implement the logEntry method using {@link Optional#ifPresentOrElse(Consumer, Runnable)}.
     * Given a cache key, this method should print a message to the standard outputstream (System.out.println(...)).
     * - If the cache has an entry for this key, print "Value: " followed by the cached value
     * - If the cache does not contain an entry for this key, print "No entry found!"
     */
    public void logEntry(K key) {
        find(key).ifPresentOrElse(
                v -> System.out.println("Value: " + v),
                () -> System.out.println("No entry found!")
        );
    }

    // ----------------------------------------------------------------------------------------------------
    // NO MORE EXERCISES BELOW!
    // ----------------------------------------------------------------------------------------------------

    public V getOrLoad(K key, Supplier<V> valueSupplier) {
        return find(key).orElseGet(() -> {
            V newValue = valueSupplier.get();
            put(key, newValue);
            return newValue;
        });
    }

    public boolean put(K key, V value) {
        if (memoryCache.put(key, value)) {
            return true;
        }
        if (diskCache.isFull()) {
            return false;
        }

        Iterator<Entry<K, V>> memEntries = memoryCache.getEntries().iterator();
        Entry<K, V> oldestMemEntry = memEntries.next();
        memEntries.remove();

        diskCache.put(oldestMemEntry.getKey(), oldestMemEntry.getValue());
        memoryCache.put(key, value);
        return true;
    }

    public void remove(K key) {
        memoryCache.remove(key);
        diskCache.remove(key);
    }

    public int size() {
        return sizeInMemory() + sizeOnDisk();
    }

    public int sizeInMemory() {
        return memoryCache.size();
    }

    public int sizeOnDisk() {
        return diskCache.size();
    }
}

/**
 * Simple implementation of a cache (not thread-safe!)
 */
class CacheImplementation<K, V> {

    private final LinkedHashMap<K, V> entries = new LinkedHashMap<>();

    private final int maxSize;

    public CacheImplementation(int maxSize) {
        this.maxSize = maxSize;
    }

    public Optional<V> find(K key) {
        return Optional.ofNullable(entries.get(key));
    }

    public boolean put(K key, V newValue) {
        if (entries.containsKey(key) || !isFull()) {
            entries.put(key, newValue);
            return true;
        }
        return false;
    }

    public void remove(K key) {
        entries.remove(key);
    }

    public int size() {
        return entries.size();
    }

    public boolean isFull() {
        return entries.size() == maxSize;
    }

    Set<Entry<K, V>> getEntries() {
        return entries.entrySet();
    }
}
