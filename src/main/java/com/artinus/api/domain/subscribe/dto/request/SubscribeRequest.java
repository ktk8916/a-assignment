package com.artinus.api.domain.subscribe.dto.request;

import com.artinus.api.domain.subscribe.entity.SubscribePlan;

public record SubscribeRequest(
        String phoneNumber,
        long channelId,
        SubscribePlan plan
) {
}
