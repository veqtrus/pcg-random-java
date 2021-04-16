/* SPDX-License-Identifier: BSL-1.0
Copyright (c) 2021 Pavlos Georgiou

Distributed under the Boost Software License, Version 1.0.
See accompanying file LICENSE_1_0.txt or copy at
https://www.boost.org/LICENSE_1_0.txt
*/

package org.paulgeorgiou.pcgrandom;

public class PcgRandomUnsynchronized extends PcgRandom {
    public PcgRandomUnsynchronized(PcgRandom random) {
        super(random);
    }

    public PcgRandomUnsynchronized(Permutation permutation, long seed, long increment) {
        super(permutation, seed, increment);
    }

    public PcgRandomUnsynchronized(Permutation permutation, long seed) {
        super(permutation, seed);
    }

    public PcgRandomUnsynchronized(Permutation permutation) {
        super(permutation);
    }

    public PcgRandomUnsynchronized(long seed, long increment) {
        super(seed, increment);
    }

    public PcgRandomUnsynchronized(long seed) {
        super(seed);
    }

    public PcgRandomUnsynchronized() {
        super(getRandomSeed(), getIncrementFromCurrentThread());
    }

    @Override
    public int nextInt() {
        return permutation.permute(updateState());
    }
}
