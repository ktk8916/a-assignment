package com.artinus.api.domain.channel.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChannelTest {

    @DisplayName("채널이 구독 가능한지 알 수 있다.")
    @ParameterizedTest
    @MethodSource("com.artinus.api.domain.channel.entity.ChannelTypeTest#provideSubscribeAbleType")
    void isSubscribeAbleChannel(ChannelType type, boolean expected) {
        // given
        Channel channel = Channel.builder()
                .type(type)
                .build();

        // when
        boolean subscribeAble = channel.isSubscribeAble();

        // then
        assertThat(subscribeAble).isEqualTo(expected);
    }


    @DisplayName("채널이 구독 해지 가능한지 알 수 있다.")
    @ParameterizedTest
    @MethodSource("com.artinus.api.domain.channel.entity.ChannelTypeTest#provideUnsubscribeAbleType")
    void isUnsubscribeAble(ChannelType type, boolean expected) {
        // given
        Channel channel = Channel.builder()
                .type(type)
                .build();

        // when
        boolean unsubscribeAble = channel.isUnsubscribeAble();

        // then
        assertThat(unsubscribeAble).isEqualTo(expected);
    }

}