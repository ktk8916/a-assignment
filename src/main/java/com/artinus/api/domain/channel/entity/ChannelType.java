package com.artinus.api.domain.channel.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChannelType {

    ONLY_SUBSCRIBE,
    ONLY_UNSUBSCRIBE,
    FREEDOM

    ;

    public boolean isSubscribeAble() {
        return this != ONLY_UNSUBSCRIBE;
    }

    public boolean isUnsubscribeAble() {
        return this != ONLY_SUBSCRIBE;
    }
}
