package com.artinus.api.domain.subscribe.service;

import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.domain.subscribe.dto.response.SubscribeLogResponse;
import com.artinus.api.domain.subscribe.entity.Subscribe;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import com.artinus.api.domain.subscribe.entity.SubscribePlan;
import com.artinus.api.domain.subscribe.repository.SubscribeLogRepository;
import com.artinus.api.domain.subscribe.repository.SubscribeRepository;
import com.artinus.api.global.exception.ArtinusAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.artinus.api.global.exception.ExceptionCode.NOT_FOUND_ENTITY;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final SubscribeLogRepository subscribeLogRepository;

    @Transactional
    public long subscribeNewOrUpgrade(Member member, Channel channel, SubscribePlan plan) {
        Subscribe subscribeOrNew = findSubscribeOrNew(member, channel);
        subscribeOrNew.upgrade(plan);
        Subscribe savedSubscribe = save(subscribeOrNew);
        SubscribeLog subscribeLog = SubscribeLog.fromSubscribe(savedSubscribe, plan);
        record(subscribeLog);
        return savedSubscribe.getId();
    }

    @Transactional
    public long unsubscribeOrDowngrade(Member member, Channel channel, SubscribePlan plan) {
        Subscribe findSubscribe = findSubscribeBy(member, channel);
        findSubscribe.downgrade(plan);
        Subscribe savedSubscribe = save(findSubscribe);
        SubscribeLog subscribeLog = SubscribeLog.fromUnsubscribe(savedSubscribe, plan);
        record(subscribeLog);
        return savedSubscribe.getId();
    }

    public List<SubscribeLogResponse> getSubscribeLogsByPhoneNumber(String phoneNumber) {
        return subscribeLogRepository.findByPhoneNumber(new PhoneNumber(phoneNumber))
                .stream()
                .map(SubscribeLogResponse::from)
                .toList();
    }

    public List<SubscribeLogResponse> findChannelSubscribeLogs(long channelId, LocalDate date) {
        return subscribeLogRepository.findByChannelBy(
                channelId,
                        date.atStartOfDay(),
                        date.atTime(23, 59, 59))
                .stream()
                .map(SubscribeLogResponse::from)
                .toList();
    }

    private Subscribe findSubscribeOrNew(Member member, Channel channel) {
        return findByMemberAndChannel(member, channel)
                .orElseGet(() -> Subscribe.byNewSubscriber(member, channel));
    }
    private Subscribe findSubscribeBy(Member member, Channel channel) {
        return findByMemberAndChannel(member, channel)
                .orElseThrow(() -> new ArtinusAppException(NOT_FOUND_ENTITY));
    }

    private Subscribe save(Subscribe subscribe) {
        return subscribeRepository.save(subscribe);
    }

    private void record(SubscribeLog subscribeLog) {
        subscribeLogRepository.save(subscribeLog);
    }

    private Optional<Subscribe> findByMemberAndChannel(Member member, Channel channel) {
        return subscribeRepository.findByMemberAndChannel(member, channel);
    }
}
