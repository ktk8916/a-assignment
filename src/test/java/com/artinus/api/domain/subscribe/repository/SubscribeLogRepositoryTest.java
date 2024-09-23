package com.artinus.api.domain.subscribe.repository;

import com.artinus.api.SpringBootTestSupport;
import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.channel.repository.ChannelRepository;
import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.repository.MemberRepository;
import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.domain.subscribe.entity.Subscribe;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class SubscribeLogRepositoryTest extends SpringBootTestSupport {

    @Autowired
    private SubscribeLogRepository subscribeLogRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ChannelRepository channelRepository;

    @DisplayName("구독 id에 대한 구독 로그를 조회한다.")
    @Test
    void findBySubscribeId() {
        // given
        Subscribe subscribe = Subscribe.builder()
                .id(1L)
                .build();

        List<SubscribeLog> subscribeLogs = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            SubscribeLog subscribeLog = SubscribeLog.builder()
                    .subscribe(subscribe)
                    .build();
            subscribeLogs.add(subscribeLog);
        }

        subscribeLogRepository.saveAll(subscribeLogs);

        // when
        List<SubscribeLog> findSubscribeLogs = subscribeLogRepository.findBySubscribeId(1L);

        // then
        assertThat(findSubscribeLogs.size()).isEqualTo(5);
    }

    @DisplayName("회원 핸드폰 번호로 구독 로그를 조회한다.")
    @Test
    void findByPhoneNumber() {
        // given
        PhoneNumber phoneNumber = new PhoneNumber("01011112222");
        Member member = Member.builder()
                .phoneNumber(phoneNumber)
                .build();
        memberRepository.save(member);
        List<SubscribeLog> subscribeLogs = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            SubscribeLog subscribeLog = SubscribeLog.builder()
                    .member(member)
                    .build();
            subscribeLogs.add(subscribeLog);
        }

        subscribeLogRepository.saveAll(subscribeLogs);

        // when
        List<SubscribeLog> findSubscribeLogs = subscribeLogRepository.findByPhoneNumber(phoneNumber);

        // then
        assertThat(findSubscribeLogs.size()).isEqualTo(5);
    }

    @DisplayName("체널의 구독 로그를 생성일 별로 확인한다.")
    @Test
    void findByChannelAndCreatedDay() {
        // given
        Channel channel = Channel.builder()
                .build();
        Channel savedChannel = channelRepository.save(channel);
//        Channel.builder().
//        LocalDateTime.of(2024, 9, 24, )
        // when

        // then
    }
}