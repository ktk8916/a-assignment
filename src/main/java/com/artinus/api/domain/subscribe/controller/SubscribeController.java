package com.artinus.api.domain.subscribe.controller;

import com.artinus.api.domain.subscribe.dto.response.SubscribeLogResponse;
import com.artinus.api.domain.subscribe.service.SubscribeService;
import com.artinus.api.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SubscribeController {

    private final SubscribeService subscribeService;

    @GetMapping("/v1/subscribe/members")
    public ApiResponse<List<SubscribeLogResponse>> getSubscribeLogsByMember(@RequestParam String phoneNumber) {
        List<SubscribeLogResponse> response = subscribeService.getSubscribeLogsByPhoneNumber(phoneNumber);
        return ApiResponse.success(response);
    }

    @GetMapping("/v1/channels/{channelId}/subscribe")
    public ApiResponse<List<SubscribeLogResponse>> getChannelSubscribeLogs(
            @PathVariable long channelId,
            @RequestParam @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date
    ) {
        List<SubscribeLogResponse> response = subscribeService.findChannelSubscribeLogs(channelId, date);
        return ApiResponse.success(response);
    }
}
