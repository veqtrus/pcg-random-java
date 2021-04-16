/* SPDX-License-Identifier: BSL-1.0
Copyright (c) 2021 Pavlos Georgiou

Distributed under the Boost Software License, Version 1.0.
See accompanying file LICENSE_1_0.txt or copy at
https://www.boost.org/LICENSE_1_0.txt
*/

package org.paulgeorgiou.pcgrandom;

public final class XorShiftRandomShift implements Permutation {
    private static final XorShiftRandomShift instance = new XorShiftRandomShift();

    private XorShiftRandomShift() {}

    @Override
    public int permute(long state) {
        final int shift = (int) ((state >>> 61) + 22);
        return (int) (((state >>> 22) ^ state) >>> shift);
    }

    @Override
    public String getName() {
        return "XSH-RS";
    }

    public static XorShiftRandomShift getInstance() {
        return instance;
    }
}
