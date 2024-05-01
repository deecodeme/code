/* (C)2024 */
package com.poc.code.practices.demo.functionalInterfaces;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class ComposabilityTest {

    @Test
    void compose() {
        Float result =
                Composability.compose(
                        2,
                        (num) -> num * num,
                        (sqrdNum) -> sqrdNum * 10.f,
                        (tenTimesSqrdNum) -> tenTimesSqrdNum + 100);
        assertThat(result).isEqualTo(140);
    }
}
