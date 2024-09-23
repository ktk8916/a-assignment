package com.artinus.api.domain.subscribe.dto.request;

import com.artinus.api.domain.subscribe.entity.SubscribePlan;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record SubscribeRequest(
        @NotEmpty(message = "...")
        String phoneNumber,
        @Positive(message = "...")
        long channelId,

        // TODO : enum validation -> custom annotation...
        SubscribePlan plan
) {
}
