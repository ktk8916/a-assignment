package com.artinus.api.domain.subscribe.dto.request;

import com.artinus.api.domain.subscribe.entity.SubscribePlan;

public record UnsubscribeRequest(
        String phoneNumber,
        long channelId,
        SubscribePlan plan
) {
}
