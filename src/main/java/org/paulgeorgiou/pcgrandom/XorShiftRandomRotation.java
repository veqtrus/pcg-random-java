/* SPDX-License-Identifier: BSL-1.0
Copyright (c) 2021 Pavlos Georgiou

Distributed under the Boost Software License, Version 1.0.
See accompanying file LICENSE_1_0.txt or copy at
https://www.boost.org/LICENSE_1_0.txt
*/

package org.paulgeorgiou.pcgrandom;

public final class XorShiftRandomRotation implements Permutation {
    private static final XorShiftRandomRotation instance = new XorShiftRandomRotation();

    private XorShiftRandomRotation() {}

    @Override
    public int permute(long state) {
        final int xorShifted = (int) (((state >>> 18) ^ state) >>> 27);
        final int rotation = (int) (state >>> 59);
        return Integer.rotateRight(xorShifted, rotation);
    }

    @Override
    public String getName() {
        return "XSH-RR";
    }

    public static XorShiftRandomRotation getInstance() {
        return instance;
    }
}
