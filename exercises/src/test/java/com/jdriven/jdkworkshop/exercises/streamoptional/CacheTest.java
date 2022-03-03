package com.jdriven.jdkworkshop.exercises.streamoptional;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import org.apache.commons.io.output.TeeOutputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheTest {

    private ByteArrayOutputStream capturedSystemOut;
    private PrintStream originalSystemOut;

    @BeforeEach
    public void setUp() {
        originalSystemOut = System.out;
        capturedSystemOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(new TeeOutputStream(originalSystemOut, capturedSystemOut)));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void testCacheShouldMoveEntriesToDiskIfMemoryIsFull() {
        Cache<Integer, String> cache = new Cache<>();

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");
        cache.put(5, "five");
        cache.put(6, "six");
        cache.put(7, "seven");
        cache.put(8, "eight");
        cache.put(9, "nine");
        cache.put(10, "ten");

        assertThat(cache.size()).isEqualTo(10);
        assertThat(cache.sizeInMemory()).isEqualTo(3);
        assertThat(cache.sizeOnDisk()).isEqualTo(7);
    }

    @Test
    public void testCacheShouldFindCachedEntries() {
        Cache<Integer, String> cache = new Cache<>();

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");
        cache.put(5, "five");

        assertThat(cache.find(1)).hasValue("one");
        assertThat(cache.find(2)).hasValue("two");
        assertThat(cache.find(3)).hasValue("three");
        assertThat(cache.find(4)).hasValue("four");
        assertThat(cache.find(5)).hasValue("five");
        assertThat(cache.find(6)).isEmpty();
        assertThat(cache.find(1, 6, 2, 7, 3, 8, 4, 9, 5, 10))
                .containsExactly("one", "two", "three", "four", "five");
    }

    @Test
    public void testCacheShouldLoadUncachedEntriesAndPutThemInCache() {
        int key = 1;
        String newValue = "new value";
        AtomicInteger callCounter = new AtomicInteger();
        Supplier<String> valueSupplier = () -> {
            callCounter.incrementAndGet();
            return newValue;
        };

        Cache<Integer, String> cache = new Cache<>();

        // Call getOrLoad twice, should return the same value twice, but only call valueSupplier once
        assertThat(cache.getOrLoad(key, valueSupplier)).isEqualTo(newValue);
        assertThat(cache.getOrLoad(key, valueSupplier)).isEqualTo(newValue);
        assertThat(callCounter.get()).isEqualTo(1);

        // Remove from cache and call getOrLoad again, valueSupplier should be called again
        cache.remove(key);
        assertThat(cache.getOrLoad(key, valueSupplier)).isEqualTo(newValue);
        assertThat(callCounter.get()).isEqualTo(2);
    }

    @Test
    public void testLogEntryShouldLogCacheEntryToSystemOutIfEntryIsFound() {
        String key = "key";
        Cache<String, String> cache = new Cache<>();
        cache.put(key, "the value");

        cache.logEntry(key);

        assertThat(getCapturedSystemOutPrint()).isEqualToIgnoringNewLines("Value: the value");
    }

    @Test
    public void testLogEntryShouldLogDefaultMessageToSystemOutIfNoEntryIsFound() {
        String key = "key";
        Cache<String, String> cache = new Cache<>();

        cache.logEntry(key);

        assertThat(getCapturedSystemOutPrint()).isEqualToIgnoringNewLines("No entry found!");
    }

    private String getCapturedSystemOutPrint() {
        return capturedSystemOut.toString();
    }

}
