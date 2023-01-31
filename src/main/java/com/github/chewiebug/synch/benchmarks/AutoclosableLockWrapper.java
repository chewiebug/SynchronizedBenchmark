package com.github.chewiebug.synch.benchmarks;

import java.io.Closeable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class AutoclosableLockWrapper implements Closeable {
    private final Lock lock;

    public AutoclosableLockWrapper(Lock lock) {
        this.lock = lock;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return this.lock.tryLock(time, unit);
    }

    @Override
    public void close() {
        // implementation is flawed: if tryLock() can't acquire the lock unlock() will throw an
        // IllegalMonitorStateException (at least with ReentrantLock)
        this.lock.unlock();
    }
}
