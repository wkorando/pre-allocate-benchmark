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
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@State(Scope.Benchmark)
public class BenchmarkPreAllocated {

//	@Param({ "10", "100", "1000" })
	@Param({ "1000"})

	public int capacity;

//	@Param({ "false", "true" })
	@Param({ "false"})
	public boolean readFromCollection;

	@Benchmark
	public void testHashMapConstructor() {
		String myString = "myString";
		Map<Integer, String> mapOfStrings = new HashMap<>(capacity*2);
		for (int i = 0; i < capacity; i++) {
			mapOfStrings.put(Integer.valueOf(i), myString + i);
			if (readFromCollection) {
				String value = mapOfStrings.get(Integer.valueOf(i));
				System.out.println(value);
			}
		}
	}
	
	@Benchmark
	public void testHashMapFactory() {
		String myString = "myString";
        Map<Integer, String> mapOfStrings = HashMap.newHashMap(capacity);
		for (int i = 0; i < capacity; i++) {
			mapOfStrings.put(Integer.valueOf(i), myString + i);
			if (readFromCollection) {
				String value = mapOfStrings.get(Integer.valueOf(i));
				System.out.println(value);
			}
		}
	}
	

	@Benchmark
	public void testHashSetConstructor() {
			String myString = "myString";
		Set<String> mapOfStrings = new HashSet<>(capacity*2);
		for (int i = 0; i < capacity; i++) {
			mapOfStrings.add(myString + i);
			if (readFromCollection) {
				System.out.println(mapOfStrings.contains(myString + i));
			}
		}
	}	
	
	@Benchmark
	public void testHashSetFactory() {
		String myString = "myString";
		Set<String> mapOfStrings = HashSet.newHashSet(capacity);
		for (int i = 0; i < capacity; i++) {
			mapOfStrings.add(myString + i);
			if (readFromCollection) {
				System.out.println(mapOfStrings.contains(myString + i));
			}
		}
	}

	

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(BenchmarkPreAllocated.class.getSimpleName())
				.build();
		new Runner(opt).run();
	}
}
