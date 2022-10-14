/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 8, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 8, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(6)
@State(Scope.Benchmark)
public class BenchmarkPreAllocated {

	@Param({ "10", "20", "30", "75", "100", "1000", "2000" })
	public int capacity;

	@Benchmark
	public void testHashMapConstructor() {
		Map<Integer, Long> mapOfStrings = new HashMap<>(capacity);
		for (int i = 0; i < capacity; i++) {
			mapOfStrings.put(i, System.currentTimeMillis());
		}
	}

	@Benchmark
	public void testHashMapFactory() {
		Map<Integer, Long> mapOfStrings = HashMap.newHashMap(capacity);
		for (int i = 0; i < capacity; i++) {
			mapOfStrings.put(Integer.valueOf(i), System.currentTimeMillis());
		}
	}

	@Benchmark
	public void testHashSetConstructor() {
		Set<Long> setOfStrings = new HashSet<>(capacity);
		for (int i = 0; i < capacity; i++) {
			setOfStrings.add(System.currentTimeMillis());
		}
	}

	@Benchmark
	public void testHashSetFactory() {
		Set<Long> setOfStrings = HashSet.newHashSet(capacity);
		for (int i = 0; i < capacity; i++) {
			setOfStrings.add(System.currentTimeMillis());
		}
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(BenchmarkPreAllocated.class.getSimpleName()).build();
		new Runner(opt).run();
	}
}
