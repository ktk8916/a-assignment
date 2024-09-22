package com.artinus.api.domain.subscribe.entity;

import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscribeLog extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Subscribe subscribe;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Channel channel;
    @Enumerated
    private SubscribePlan plan;
    @Enumerated
    private SubscribeAction action;

    public long getMemberId() {
        return member.getId();
    }

    public long getChannelId() {
        return channel.getId();
    }

    public static SubscribeLog fromSubscribe(Subscribe subscribe, SubscribePlan plan) {
        return SubscribeLog.builder()
                .subscribe(subscribe)
                .member(subscribe.getMember())
                .channel(subscribe.getChannel())
                .plan(plan)
                .action(SubscribeAction.SUBSCRIBE)
                .build();
    }

    public static SubscribeLog fromUnsubscribe(Subscribe subscribe, SubscribePlan plan) {
        return SubscribeLog.builder()
                .subscribe(subscribe)
                .member(subscribe.getMember())
                .channel(subscribe.getChannel())
                .plan(plan)
                .action(SubscribeAction.UNSUBSCRIBE)
                .build();
    }

    @Builder
    private SubscribeLog(Long id, Subscribe subscribe, Member member, Channel channel, SubscribePlan plan, SubscribeAction action) {
        this.id = id;
        this.subscribe = subscribe;
        this.member = member;
        this.channel = channel;
        this.plan = plan;
        this.action = action;
    }
}
