package com.artinus.api.domain.subscribe.dto.response;

import com.artinus.api.domain.subscribe.entity.SubscribeAction;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import com.artinus.api.domain.subscribe.entity.SubscribePlan;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChannelSubscribeLogResponse(
        long id,
        long memberId,
        SubscribeAction action,
        SubscribePlan plan,
        LocalDateTime date
) {
    public static ChannelSubscribeLogResponse from(SubscribeLog subscribeLog) {
        return ChannelSubscribeLogResponse.builder()
                .id(subscribeLog.getId())
                .memberId(subscribeLog.getMemberId())
                .action(subscribeLog.getAction())
                .plan(subscribeLog.getPlan())
                .date(subscribeLog.getCreatedAt())
                .build();
    }
}
