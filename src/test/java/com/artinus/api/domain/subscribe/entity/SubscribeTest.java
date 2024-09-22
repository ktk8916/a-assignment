package com.artinus.api.domain.subscribe.entity;

import com.artinus.api.global.exception.ArtinusAppException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SubscribeTest {

    @DisplayName("구독을 상위 플랜으로 업그레이드한다.")
    @ParameterizedTest
    @MethodSource("provideUpgradeSubscribePlan")
    void upgrade(SubscribePlan originPlan, SubscribePlan newPlan) {
        // given
        Subscribe subscribe = Subscribe.builder()
                .plan(originPlan)
                .build();

        // when
        subscribe.upgrade(newPlan);
    
        // then
        assertThat(subscribe.getPlan()).isEqualTo(newPlan);
    }
    
    @DisplayName("기존 플랜보다 상위 플랜으로만 업그레이드 할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideUpgradeToNotUpperPlan")
    void upgradeToNotUpperPlan(SubscribePlan originPlan, SubscribePlan newPlan) {
        // given
        Subscribe subscribe = Subscribe.builder()
                .plan(originPlan)
                .build();

        // when, then
        assertThatThrownBy(() -> subscribe.upgrade(newPlan))
                .isInstanceOf(ArtinusAppException.class)
                .hasMessage("invalid subscribe plan");
    }

    @DisplayName("구독을 하위 플랜으로 다운그레이드한다.")
    @ParameterizedTest
    @MethodSource("provideDowngradeSubscribePlan")
    void downgrade(SubscribePlan originPlan, SubscribePlan newPlan) {
        // given
        Subscribe subscribe = Subscribe.builder()
                .plan(originPlan)
                .build();

        // when
        subscribe.downgrade(newPlan);

        // then
        assertThat(subscribe.getPlan()).isEqualTo(newPlan);
    }

    @DisplayName("기존 플랜보다 하위 플랜으로만 다운그레이드 할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideDowngradeToNotLowerPlan")
    void downgradeToNotLowerPlan(SubscribePlan originPlan, SubscribePlan newPlan) {
        // given
        Subscribe subscribe = Subscribe.builder()
                .plan(originPlan)
                .build();

        // when, then
        assertThatThrownBy(() -> subscribe.downgrade(newPlan))
                .isInstanceOf(ArtinusAppException.class)
                .hasMessage("invalid subscribe plan");
    }

    static Stream<Arguments> provideUpgradeSubscribePlan() {
        return Stream.of(
                Arguments.of(SubscribePlan.NONE, SubscribePlan.BASIC),
                Arguments.of(SubscribePlan.NONE, SubscribePlan.PREMIUM),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.PREMIUM)
        );
    }

    static Stream<Arguments> provideDowngradeSubscribePlan() {
        return Stream.of(
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.BASIC),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.NONE),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.NONE)
        );
    }

    static Stream<Arguments> provideUpgradeToNotUpperPlan() {
        return Stream.of(
                Arguments.of(SubscribePlan.NONE, SubscribePlan.NONE),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.NONE),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.BASIC),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.NONE),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.BASIC),
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.PREMIUM)
        );
    }

    static Stream<Arguments> provideDowngradeToNotLowerPlan() {
        return Stream.of(
                Arguments.of(SubscribePlan.PREMIUM, SubscribePlan.PREMIUM),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.PREMIUM),
                Arguments.of(SubscribePlan.BASIC, SubscribePlan.BASIC),
                Arguments.of(SubscribePlan.NONE, SubscribePlan.PREMIUM),
                Arguments.of(SubscribePlan.NONE, SubscribePlan.BASIC),
                Arguments.of(SubscribePlan.NONE, SubscribePlan.NONE)
        );
    }
}