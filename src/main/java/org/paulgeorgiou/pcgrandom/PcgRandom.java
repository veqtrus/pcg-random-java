/* SPDX-License-Identifier: BSL-1.0
Copyright (c) 2021 Pavlos Georgiou

Distributed under the Boost Software License, Version 1.0.
See accompanying file LICENSE_1_0.txt or copy at
https://www.boost.org/LICENSE_1_0.txt
*/

package org.paulgeorgiou.pcgrandom;

import java.util.Objects;
import java.util.Random;

public class PcgRandom extends Random {
    public static final long DEFAULT_INCREMENT = 1442695040888963407L;

    private static final PcgRandom instance = new PcgRandom((new Random()).nextLong());

    protected long state;
    protected final long increment;
    protected final Permutation permutation;

    public PcgRandom(PcgRandom random) {
        this.state = random.state;
        this.increment = random.increment;
        this.permutation = random.permutation;
    }

    public PcgRandom(Permutation permutation, long seed, long increment) {
        this.increment = (increment | 1);
        this.permutation = permutation;
        setSeed(seed);
    }

    public PcgRandom(Permutation permutation, long seed) {
        this.increment = DEFAULT_INCREMENT;
        this.permutation = permutation;
        setSeed(seed);
    }

    public PcgRandom(Permutation permutation) {
        this.state = getRandomSeed();
        this.increment = DEFAULT_INCREMENT;
        this.permutation = permutation;
    }

    public PcgRandom(long seed, long increment) {
        this.increment = (increment | 1);
        this.permutation = XorShiftRandomRotation.getInstance();
        setSeed(seed);
    }

    public PcgRandom(long seed) {
        this.increment = DEFAULT_INCREMENT;
        this.permutation = XorShiftRandomRotation.getInstance();
        setSeed(seed);
    }

    public PcgRandom() {
        this.state = getRandomSeed();
        this.increment = DEFAULT_INCREMENT;
        this.permutation = XorShiftRandomRotation.getInstance();
    }

    public static long getRandomSeed() {
        return instance.nextLong();
    }

    public static long getIncrementFromStreamNumber(long stream) {
        return (stream << 1) | 1;
    }

    public static long getIncrementFromThread(Thread thread) {
        return getIncrementFromStreamNumber(thread.getId());
    }

    public static long getIncrementFromCurrentThread() {
        return getIncrementFromThread(Thread.currentThread());
    }

    public long getStreamNumber() {
        return increment >>> 1;
    }

    public Permutation getPermutation() {
        return permutation;
    }

    protected final long updateState() {
        final long oldState = state;
        state = oldState * 6364136223846793005L + increment;
        return oldState;
    }

    @Override
    public synchronized void setSeed(long seed) {
        super.setSeed(0);
        state = 0;
        updateState();
        state += seed;
        updateState();
    }

    @Override
    protected int next(int bits) {
        return nextInt() & ((1 << bits) - 1);
    }

    @Override
    public int nextInt() {
        final long oldState;
        synchronized (this) {
            oldState = updateState();
        }
        return permutation.permute(oldState);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof PcgRandom) {
            PcgRandom other = (PcgRandom) o;
            return state == other.state && increment == other.increment && other.permutation.equals(permutation);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, increment, permutation);
    }

    @Override
    public String toString() {
        return "PCG-" + permutation.getName();
    }
}
