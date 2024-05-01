/* (C)2024 */
package com.poc.code.practices.demo.functionalInterfaces;

import java.util.function.Function;

public class Composability {
    public static <A, B, C, D> D compose(
            A num, Function<A, B> first, Function<B, C> second, Function<C, D> third) {
        return first.andThen(second).andThen(third).apply(num);
    }
}
