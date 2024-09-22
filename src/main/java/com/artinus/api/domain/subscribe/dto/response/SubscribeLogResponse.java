package com.artinus.api.domain.subscribe.dto.response;

import com.artinus.api.domain.subscribe.entity.SubscribeAction;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import com.artinus.api.domain.subscribe.entity.SubscribePlan;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SubscribeLogResponse(
        long ChannelId,
        SubscribeAction action,
        SubscribePlan plan,
        LocalDateTime date
) {
    public static SubscribeLogResponse from(SubscribeLog subscribeLog) {
        return SubscribeLogResponse.builder()
                .ChannelId(subscribeLog.getChannelId())
                .action(subscribeLog.getAction())
                .date(subscribeLog.getCreatedAt())
                .build();
    }
}
