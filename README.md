# Pre-Allocate Benchmark
This project is a JMH benchmark of the changes added from JBS: https://bugs.openjdk.org/browse/JDK-8284377, which added factory methods to `HashMap`, `HashSet` and others that created a properly sized pre-allocated collection to avoid resizing. 

Benchmark testing, initially intended to show the performance improvement, showed a consistent, if small performance regression when using the pre-allocated factory. 

## System specs
* OS: macOS Montery (12.6)
* Processor: 2.6 GHz 6-Core Intel Core i7
* Memory: 32 GB 2667 MHz DDR4

## Test Scenarios
### Results All 
**Scenario:** Add and read 10, 100, and 1000 elements to a HashSet and HashMap using constructor and static factor.

**Results:** results-all.txt

### Results add only 10 count
**Scenario:** Add 10 elements to a HashSet and HashMap using constructor and static factor.

**Results:** results-add-only-10-count.txt

### Results add only 10 and 100 count
**Scenario:** Add 10 and 100 elements to a HashSet and HashMap using constructor and static factor.

**Results:** results-add-only-10-and-100-count.txt

### Results read 10 and 100 count
**Scenario:** Add and read 10 and 100 elements to a HashSet and HashMap using constructor and static factor.

**Results:** results-read-10-and-100-count.txt

### Results double constructor
**Scenario:** Add and read 10 and 100 elements to a HashSet and HashMap using constructor and static factor. Value passed to the consturctor is double the expected capacity

**Results:** results-double.txt
