package com.artinus.api.domain.subscribe.service;

import com.artinus.api.SpringBootTestSupport;
import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.domain.subscribe.dto.response.ChannelSubscribeLogResponse;
import com.artinus.api.domain.subscribe.dto.response.MemberSubscribeLogResponse;
import com.artinus.api.domain.subscribe.entity.SubscribeAction;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import com.artinus.api.domain.subscribe.entity.SubscribePlan;
import com.artinus.api.domain.subscribe.repository.SubscribeLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class SubscribeLogQueryServiceTest extends SpringBootTestSupport {

    @Autowired
    private SubscribeLogQueryService subscribeLogQueryService;
    @MockBean
    private SubscribeLogRepository subscribeLogRepository;
    
    @DisplayName("회원 핸드폰 번호로 조회한 구독 로그를 원하는 양식으로 변환한다.")
    @Test
    void getByPhoneNumber() {
        // given
        List<SubscribeLog> logs = new ArrayList<>();

        SubscribeLog subscribeLog1 = SubscribeLog.builder()
                .id(1L)
                .channel(Channel.builder().id(22L).build())
                .plan(SubscribePlan.PREMIUM)
                .action(SubscribeAction.SUBSCRIBE)
                .build();

        SubscribeLog subscribeLog2 = SubscribeLog.builder()
                .id(2L)
                .channel(Channel.builder().id(27L).build())
                .plan(SubscribePlan.NONE)
                .action(SubscribeAction.UNSUBSCRIBE)
                .build();

        logs.add(subscribeLog1);
        logs.add(subscribeLog2);

        when(subscribeLogRepository.findByPhoneNumber(any(PhoneNumber.class)))
                .thenReturn(logs);

        // when
        List<MemberSubscribeLogResponse> findLogs = subscribeLogQueryService.getByPhoneNumber("01011112222");

        // then
        assertThat(findLogs)
                .hasSize(2)
                .extracting("id", "channelId", "plan", "action", "date")
                .containsExactlyInAnyOrder(
                        tuple(1L, 22L, SubscribePlan.PREMIUM, SubscribeAction.SUBSCRIBE, null),
                        tuple(2L, 27L, SubscribePlan.NONE, SubscribeAction.UNSUBSCRIBE, null)
                );
    }

    @DisplayName("채널 구독 로그를 원하는 양식으로 변환한다.")
    @Test
    void getChannelLogs() {
        // given
        List<SubscribeLog> logs = new ArrayList<>();

        SubscribeLog subscribeLog1 = SubscribeLog.builder()
                .id(1L)
                .member(Member.builder().id(22L).build())
                .plan(SubscribePlan.PREMIUM)
                .action(SubscribeAction.SUBSCRIBE)
                .build();

        SubscribeLog subscribeLog2 = SubscribeLog.builder()
                .id(2L)
                .member(Member.builder().id(27L).build())
                .plan(SubscribePlan.NONE)
                .action(SubscribeAction.UNSUBSCRIBE)
                .build();

        logs.add(subscribeLog1);
        logs.add(subscribeLog2);

        when(subscribeLogRepository.findByChannelAndCreatedDay(anyLong(), any(LocalDate.class)))
                .thenReturn(logs);

        // when
        List<ChannelSubscribeLogResponse> findLogs = subscribeLogQueryService.getChannelLogs(1L, LocalDate.now());

        // then
        assertThat(findLogs)
                .hasSize(2)
                .extracting("id", "memberId", "plan", "action", "date")
                .containsExactlyInAnyOrder(
                        tuple(1L, 22L, SubscribePlan.PREMIUM, SubscribeAction.SUBSCRIBE, null),
                        tuple(2L, 27L, SubscribePlan.NONE, SubscribeAction.UNSUBSCRIBE, null)
                );
    }
}