package com.artinus.api.domain.subscribe.service;

import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.domain.subscribe.dto.response.ChannelSubscribeLogResponse;
import com.artinus.api.domain.subscribe.dto.response.MemberSubscribeLogResponse;
import com.artinus.api.domain.subscribe.repository.SubscribeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscribeLogQueryService {

    private final SubscribeLogRepository subscribeLogRepository;

    public List<MemberSubscribeLogResponse> getByPhoneNumber(String phoneNumber) {
        return subscribeLogRepository.findByPhoneNumber(new PhoneNumber(phoneNumber))
                .stream()
                .map(MemberSubscribeLogResponse::from)
                .toList();
    }

    public List<ChannelSubscribeLogResponse> getChannelLogs(long channelId, LocalDate date) {
        return subscribeLogRepository.findByChannelAndCreatedDay(channelId, date)
                .stream()
                .map(ChannelSubscribeLogResponse::from)
                .toList();
    }
}
