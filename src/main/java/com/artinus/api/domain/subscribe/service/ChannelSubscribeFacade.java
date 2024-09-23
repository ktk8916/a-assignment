package com.artinus.api.domain.subscribe.service;

import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.channel.sevice.ChannelService;
import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.service.MemberService;
import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.domain.subscribe.dto.request.SubscribeRequest;
import com.artinus.api.domain.subscribe.dto.request.UnsubscribeRequest;
import com.artinus.api.domain.subscribe.dto.response.SubscribeIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelSubscribeFacade {

    private final MemberService memberService;
    private final ChannelService channelService;
    private final SubscribeService subscribeService;

    public SubscribeIdResponse subscribe(SubscribeRequest request) {
        Member member = memberService.findByPhoneNumber(new PhoneNumber(request.phoneNumber()));
        Channel channel = channelService.findSubscribeAbleById(request.channelId());
        long subscribeId = subscribeService.subscribeNewOrUpgrade(member, channel, request.plan());
        return new SubscribeIdResponse(subscribeId);
    }

    public SubscribeIdResponse unsubscribe(UnsubscribeRequest request) {
        Member member = memberService.findByPhoneNumber(new PhoneNumber(request.phoneNumber()));
        Channel channel = channelService.findUnsubscribeAbleById(request.channelId());
        long subscribeId = subscribeService.unsubscribeOrDowngrade(member, channel, request.plan());
        return new SubscribeIdResponse(subscribeId);
    }
}
