package com.artinus.api.domain.subscribe.entity;

import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.global.BaseEntity;
import com.artinus.api.global.exception.ArtinusAppException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.artinus.api.global.exception.ExceptionCode.INVALID_SUBSCRIBE_PLAN;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscribe extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SubscribePlan plan;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Channel channel;

    public static Subscribe byNewSubscriber(Member member, Channel channel) {
        return Subscribe.builder()
                .member(member)
                .channel(channel)
                .plan(SubscribePlan.NONE)
                .build();
    }

    public void upgrade(SubscribePlan newPlan) {
        if (!newPlan.isUpperPlanThan(this.plan)) {
            throw new ArtinusAppException(INVALID_SUBSCRIBE_PLAN);
        }
        this.plan = newPlan;
    }

    public void downgrade(SubscribePlan newPlan) {
        if (!newPlan.isLowerPlanThan(this.plan)) {
            throw new ArtinusAppException(INVALID_SUBSCRIBE_PLAN);
        }
        this.plan = newPlan;
    }

    @Builder
    private Subscribe(Long id, SubscribePlan plan, Member member, Channel channel) {
        this.id = id;
        this.plan = plan;
        this.member = member;
        this.channel = channel;
    }
}
