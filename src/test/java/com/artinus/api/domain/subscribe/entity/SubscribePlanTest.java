package com.artinus.api.domain.subscribe.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SubscribePlanTest {

    @DisplayName("구독 플랜이 새로운 플랜보다 상위 플랜인지 확인한다.")
    @ParameterizedTest
    @MethodSource("provideUpperSubscribePlan")
    void isUpperPlanThan(SubscribePlan originPlan, SubscribePlan newPlan, boolean expected) {
        // given

        // when
        boolean isUpperPlanThan = originPlan.isUpperPlanThan(newPlan);

        // then
        assertThat(isUpperPlanThan).isEqualTo(expected);
    }

    @DisplayName("구독 플랜이 새로운 플랜보다 하위 플랜인지 확인한다.")
    @ParameterizedTest
    @MethodSource("provideLowerSubscribePlan")
    void isLowerPlanThan(SubscribePlan originPlan, SubscribePlan newPlan, boolean expected) {
        // given

        // when
        boolean isLowerPlanThan = originPlan.isLowerPlanThan(newPlan);

        // then
        assertThat(isLowerPlanThan).isEqualTo(expected);
    }

    static Stream<Arguments> provideUpperSubscribePlan() {
        return Stream.of(
                Arguments.of(SubscribePlan.NONE, SubscribePlan.NONE, false),
                Arguments.of(SubscribePlan.NONE, SubscribePlan.BASIC, false),
                Arguments.of(SubscribePlan.NONE, SubscribePlan.PREMIUM, false),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.NONE, true),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.BASIC, false),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.PREMIUM, false),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.NONE, true),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.BASIC, true),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.PREMIUM, false)
        );
    }

    static Stream<Arguments> provideLowerSubscribePlan() {
        return Stream.of(
                Arguments.of(SubscribePlan.NONE, SubscribePlan.NONE, false),
                Arguments.of(SubscribePlan.NONE, SubscribePlan.BASIC, true),
                Arguments.of(SubscribePlan.NONE, SubscribePlan.PREMIUM, true),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.NONE, false),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.BASIC, false),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.PREMIUM, true),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.NONE, false),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.BASIC, false),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.PREMIUM, false)
        );
    }
}
