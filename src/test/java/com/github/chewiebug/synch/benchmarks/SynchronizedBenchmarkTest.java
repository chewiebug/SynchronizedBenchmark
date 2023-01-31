package com.github.chewiebug.synch.benchmarks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SynchronizedBenchmarkTest {

    private SynchronizedBenchmark synchronizedBenchmark;

    @BeforeEach
    void setup() {
        synchronizedBenchmark = new SynchronizedBenchmark();
    }
    @Test
    void getEntryAutoclosableReentrantLock() {
        var value = synchronizedBenchmark.getEntryAutoclosableReentrantLock("ONE");
        assertEquals("ONE", value);
    }
}