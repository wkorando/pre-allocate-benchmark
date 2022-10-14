# Pre-Allocate Benchmark
This project is a JMH benchmark of the changes added from JBS: https://bugs.openjdk.org/browse/JDK-8284377, which added factory methods to `HashMap`, `HashSet` and others that created a properly sized pre-allocated collection to avoid resizing. 

## System specs
* OS: macOS Montery (12.6)
* Processor: 2.6 GHz 6-Core Intel Core i7
* Memory: 32 GB 2667 MHz DDR4
