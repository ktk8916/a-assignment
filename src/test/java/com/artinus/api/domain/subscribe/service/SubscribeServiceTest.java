package com.artinus.api.domain.subscribe.service;

import com.artinus.api.SpringBootTestSupport;
import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.channel.entity.ChannelType;
import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.subscribe.entity.Subscribe;
import com.artinus.api.domain.subscribe.entity.SubscribeAction;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import com.artinus.api.domain.subscribe.entity.SubscribePlan;
import com.artinus.api.domain.subscribe.repository.SubscribeLogRepository;
import com.artinus.api.domain.subscribe.repository.SubscribeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class SubscribeServiceTest extends SpringBootTestSupport {

    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private SubscribeRepository subscribeRepository;
    @Autowired
    private SubscribeLogRepository subscribeLogRepository;

    @DisplayName("원하는 플랜으로 신규 구독할 수 있다. 구독 신청 기록이 저장된다.")
    @Test
    void newSubscribe() {
        // given
        Member newSubscriber = Member.builder()
                .id(1L)
                .build();
        Channel channel = Channel.builder()
                .id(1L)
                .type(ChannelType.ONLY_SUBSCRIBE)
                .build();
        SubscribePlan plan = SubscribePlan.BASIC;

        // when
        long subscribeId = subscribeService.subscribeNewOrUpgrade(newSubscriber, channel, plan);
        Subscribe subscribe = subscribeRepository.findById(subscribeId).get();
        SubscribeLog subscribeLog = subscribeLogRepository.findBySubscribeId(subscribeId).get(0);

        // then
        assertThat(subscribe.getPlan()).isEqualTo(plan);
        assertThat(subscribeLog.getAction()).isEqualTo(SubscribeAction.SUBSCRIBE);
    }
    
    @DisplayName("기존 구독 플랜을 업그레이드 할 수 있다. 구독 업그레이드 기록이 저장된다.")
    @Test
    void upgradeSubscribe() {
        // given
        Member existSubscriber = Member.builder()
                .id(1L)
                .build();
        Channel channel = Channel.builder()
                .id(1L)
                .type(ChannelType.ONLY_SUBSCRIBE)
                .build();
        Subscribe subscribe = Subscribe.builder()
                .member(existSubscriber)
                .channel(channel)
                .plan(SubscribePlan.BASIC)
                .build();

        Subscribe existSubscribe = subscribeRepository.save(subscribe);

        // when
        subscribeService.subscribeNewOrUpgrade(existSubscriber, channel, SubscribePlan.PREMIUM);

        Subscribe findSubscribe = subscribeRepository.findById(existSubscribe.getId()).get();
        SubscribeLog subscribeLog = subscribeLogRepository.findBySubscribeId(findSubscribe.getId()).get(0);

        // then
        assertThat(findSubscribe.getPlan()).isEqualTo(SubscribePlan.PREMIUM);
        assertThat(subscribeLog.getAction()).isEqualTo(SubscribeAction.SUBSCRIBE);
    }
    
    @DisplayName("기존 구독 플랜을 다운그레이드 할 수 있다. 구독 다운그레이드 기록이 저장된다.")
    @Test
    void unsubscribeOrDowngrade() {
        // given
        Member existSubscriber = Member.builder()
                .id(1L)
                .build();
        Channel channel = Channel.builder()
                .id(1L)
                .type(ChannelType.ONLY_SUBSCRIBE)
                .build();
        Subscribe subscribe = Subscribe.builder()
                .member(existSubscriber)
                .channel(channel)
                .plan(SubscribePlan.BASIC)
                .build();

        Subscribe existSubscribe = subscribeRepository.save(subscribe);

        // when
        subscribeService.unsubscribeOrDowngrade(existSubscriber, channel, SubscribePlan.NONE);

        Subscribe findSubscribe = subscribeRepository.findById(existSubscribe.getId()).get();
        SubscribeLog subscribeLog = subscribeLogRepository.findBySubscribeId(findSubscribe.getId()).get(0);

        // then
        assertThat(findSubscribe.getPlan()).isEqualTo(SubscribePlan.NONE);
        assertThat(subscribeLog.getAction()).isEqualTo(SubscribeAction.UNSUBSCRIBE);
    }
}