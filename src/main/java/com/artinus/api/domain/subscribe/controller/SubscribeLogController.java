package com.artinus.api.domain.subscribe.controller;

import com.artinus.api.domain.subscribe.dto.response.ChannelSubscribeLogResponse;
import com.artinus.api.domain.subscribe.dto.response.MemberSubscribeLogResponse;
import com.artinus.api.domain.subscribe.service.SubscribeLogQueryService;
import com.artinus.api.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SubscribeLogController {

    private final SubscribeLogQueryService subscribeLogQueryService;

    @GetMapping("/v1/subscribe/members/logs")
    public ApiResponse<List<MemberSubscribeLogResponse>> getSubscribeLogsByMember(@RequestParam String phoneNumber) {
        List<MemberSubscribeLogResponse> response = subscribeLogQueryService.getByPhoneNumber(phoneNumber);
        return ApiResponse.success(response);
    }

    @GetMapping("/v1/subscribe/channels/{channelId}/logs")
    public ApiResponse<List<ChannelSubscribeLogResponse>> getChannelSubscribeLogs(
            @PathVariable long channelId,
            @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date
    ) {
        List<ChannelSubscribeLogResponse> response = subscribeLogQueryService.getChannelLogs(channelId, date);
        return ApiResponse.success(response);
    }
}
