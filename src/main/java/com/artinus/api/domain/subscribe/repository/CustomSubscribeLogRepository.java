package com.artinus.api.domain.subscribe.repository;

import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;

import java.time.LocalDate;
import java.util.List;

public interface CustomSubscribeLogRepository {

    List<SubscribeLog> findBySubscribeId(long subscribeId);
    List<SubscribeLog> findByPhoneNumber(PhoneNumber phoneNumber);
    List<SubscribeLog> findByChannelAndCreatedDay(long channelId, LocalDate createdDay);
}
