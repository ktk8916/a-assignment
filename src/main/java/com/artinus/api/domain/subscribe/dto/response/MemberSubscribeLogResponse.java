package com.artinus.api.domain.subscribe.dto.response;

import com.artinus.api.domain.subscribe.entity.SubscribeAction;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import com.artinus.api.domain.subscribe.entity.SubscribePlan;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberSubscribeLogResponse (
        long id,
        long channelId,
        SubscribeAction action,
        SubscribePlan plan,
        LocalDateTime date
) {
    public static MemberSubscribeLogResponse from(SubscribeLog subscribeLog) {
        return MemberSubscribeLogResponse.builder()
                .id(subscribeLog.getId())
                .channelId(subscribeLog.getChannelId())
                .action(subscribeLog.getAction())
                .plan(subscribeLog.getPlan())
                .date(subscribeLog.getCreatedAt())
                .build();
    }
}