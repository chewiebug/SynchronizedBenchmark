package com.github.chewiebug.synch.benchmarks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@Fork(value = 1, warmups = 0)
@Warmup(iterations = 0)
@Measurement(iterations = 3)
@State(Scope.Benchmark)
@Threads(value = 1)
@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(value = TimeUnit.NANOSECONDS)
public class SynchronizedBenchmark {
    @State(Scope.Benchmark)
    public static class SynchronizedBenchmarkSetup {
        private SynchronizedBenchmark synchronizedBenchmark;

        @Param({"ONE"})
        public String key;

        @Setup(Level.Invocation)
        public void setUp() {
            synchronizedBenchmark = new SynchronizedBenchmark();
        }

        public SynchronizedBenchmark getSynchronizedBenchmark() {
            return synchronizedBenchmark;
        }

        public String getKey() {
            return key;
        }
    }

    private final static ReentrantLock reentrantLock = new ReentrantLock();
    private final static AutoclosableLockWrapper autoclosableReentrantLock = new AutoclosableLockWrapper(new ReentrantLock());

    private final Map<String, String> map = new HashMap<>();

    public SynchronizedBenchmark() {
        init();
    }

    private void init() {
        map.put("ONE", "ONE");
        map.put("TWO", "TWO");
        map.put("THREE", "THREE");
        map.put("FOUR", "FOUR");
        map.put("FIVE", "FIVE");
        map.put("SIX", "SIX");
        map.put("SEVEN", "SEVEN");
        map.put("EIGHT", "EIGHT");
        map.put("NINE", "NINE");
        map.put("TEN", "TEN");

    }

    @Benchmark
    public String getEntry(SynchronizedBenchmarkSetup synchronizedBenchmarkSetup) {
        return synchronizedBenchmarkSetup.getSynchronizedBenchmark().getEntry(synchronizedBenchmarkSetup.getKey());
    }

    @Benchmark
    public String getEntrySynchronized(SynchronizedBenchmarkSetup synchronizedBenchmarkSetup) {
        return synchronizedBenchmarkSetup.getSynchronizedBenchmark().getEntrySynchronized(synchronizedBenchmarkSetup.getKey());
    }

    @Benchmark
    public String getEntryReentrantLock(SynchronizedBenchmarkSetup synchronizedBenchmarkSetup) {
        return synchronizedBenchmarkSetup.getSynchronizedBenchmark().getEntryReentrantLock(synchronizedBenchmarkSetup.getKey());
    }

    @Benchmark
    public String getEntryAutoclosableReentrantLock(SynchronizedBenchmarkSetup synchronizedBenchmarkSetup) {
        return synchronizedBenchmarkSetup.getSynchronizedBenchmark().getEntryAutoclosableReentrantLock(synchronizedBenchmarkSetup.getKey());
    }


    public String getEntry(String key) {
        return map.get(key);
    }

    public synchronized String getEntrySynchronized(String key) {
        return map.get(key);
    }

    public String getEntryReentrantLock(String key) {
        try {
            if (reentrantLock.tryLock(2, TimeUnit.SECONDS)) {
                return map.get(key);
            }
        }
        catch (InterruptedException e) {
            // ignore
        }
        finally {
            reentrantLock.unlock();
        }

        return "DEFAULT";
    }

    public String getEntryAutoclosableReentrantLock(String key) {
        try (autoclosableReentrantLock) {
            if (autoclosableReentrantLock.tryLock(2, TimeUnit.SECONDS)) {
                return map.get(key);
            } else {
                System.out.println("could not acquire lock");
            }
        }
        catch (InterruptedException e) {
            System.out.println(e);
            // ignore
        }

        return "DEFAULT";
    }
}
