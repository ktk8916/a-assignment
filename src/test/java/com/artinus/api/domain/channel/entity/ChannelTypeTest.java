package com.artinus.api.domain.channel.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChannelTypeTest {

    @DisplayName("채널 타입이 구독 가능한지 알 수 있다.")
    @ParameterizedTest
    @MethodSource("provideSubscribeAbleType")
    void isSubscribeAble(ChannelType type, boolean expected) {
        // given

        // when
        boolean subscribeAble = type.isSubscribeAble();

        // then
        assertThat(subscribeAble).isEqualTo(expected);
    }

    static Stream<Arguments> provideSubscribeAbleType() {
        return Stream.of(
                Arguments.of(ChannelType.ONLY_SUBSCRIBE, true),
                Arguments.of(ChannelType.ONLY_UNSUBSCRIBE, false),
                Arguments.of(ChannelType.FREEDOM, true)
        );
    }

    @DisplayName("채널 타입이 구독 해지 가능한지 알 수 있다.")
    @ParameterizedTest
    @MethodSource("provideUnsubscribeAbleType")
    void isUnsubscribeAble(ChannelType type, boolean expected) {
        // given

        // when
        boolean unsubscribeAble = type.isUnsubscribeAble();

        // then
        assertThat(unsubscribeAble).isEqualTo(expected);
    }

    static Stream<Arguments> provideUnsubscribeAbleType() {
        return Stream.of(
                Arguments.of(ChannelType.ONLY_SUBSCRIBE, false),
                Arguments.of(ChannelType.ONLY_UNSUBSCRIBE, true),
                Arguments.of(ChannelType.FREEDOM, true)
        );
    }
}