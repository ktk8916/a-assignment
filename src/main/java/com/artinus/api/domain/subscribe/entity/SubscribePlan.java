package com.artinus.api.domain.subscribe.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubscribePlan {
    NONE(0),
    BASIC(100),
    PREMIUM(200),

    ;

    private final int planLevel;

    public boolean isUpperPlanThan(SubscribePlan other) {
        return this.planLevel > other.planLevel;
    }

    public boolean isLowerPlanThan(SubscribePlan other) {
        return this.planLevel < other.planLevel;
    }
}
