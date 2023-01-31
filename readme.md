Goal
Find out impact of different thread synchronization implementations on performance.

Hardware:
Intel(R) Core(TM) i5-6600 CPU @ 3.30GHz   3.31 GHz
16.0 GB RAM
Windows 10 Pro, 22H2

# JMH version: 1.36
# VM version: JDK 11, OpenJDK 64-Bit Server VM, 11+28
# VM invoker: C:\Program Files\Java\openjdk-11\bin\java.exe
# VM options: -Dvisualvm.id=7777131918400 -javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2\lib\idea_rt.jar=63575:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2\bin -Dfile.encoding=UTF-8
# Blackhole mode: full + dont-inline hint (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
# Warmup: <none>
# Measurement: 3 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op

Benchmark                                                (key)  Mode  Cnt   Score    Error  Units
SynchronizedBenchmark.getEntry                             ONE  avgt    3  32.303 ±  7.828  ns/op
SynchronizedBenchmark.getEntryAutoclosableReentrantLock    ONE  avgt    3  48.665 ± 19.261  ns/op
SynchronizedBenchmark.getEntryReentrantLock                ONE  avgt    3  54.359 ± 18.930  ns/op
SynchronizedBenchmark.getEntrySynchronized                 ONE  avgt    3  39.264 ±  5.895  ns/op

# JMH version: 1.36
# VM version: JDK 17, OpenJDK 64-Bit Server VM, 17+35-2724
# VM invoker: C:\Program Files\Java\openjdk-17\bin\java.exe
# VM options: -Dvisualvm.id=7943882075200 -javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2\lib\idea_rt.jar=63605:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2\bin -Dfile.encoding=UTF-8
# Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
# Warmup: <none>
# Measurement: 3 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op

Benchmark                                                (key)  Mode  Cnt   Score    Error  Units
SynchronizedBenchmark.getEntry                             ONE  avgt    3  30.327 ±  6.989  ns/op
SynchronizedBenchmark.getEntryAutoclosableReentrantLock    ONE  avgt    3  46.567 ± 10.008  ns/op
SynchronizedBenchmark.getEntryReentrantLock                ONE  avgt    3  46.125 ± 20.910  ns/op
SynchronizedBenchmark.getEntrySynchronized                 ONE  avgt    3  40.675 ±  8.153  ns/op