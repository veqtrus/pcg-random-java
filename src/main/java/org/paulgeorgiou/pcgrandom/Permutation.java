/* SPDX-License-Identifier: BSL-1.0
Copyright (c) 2021 Pavlos Georgiou

Distributed under the Boost Software License, Version 1.0.
See accompanying file LICENSE_1_0.txt or copy at
https://www.boost.org/LICENSE_1_0.txt
*/

package org.paulgeorgiou.pcgrandom;

public interface Permutation {
    int permute(long state);

    String getName();
}
