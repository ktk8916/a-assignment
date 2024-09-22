package com.artinus.api.domain.subscribe.controller;

import com.artinus.api.domain.subscribe.dto.request.SubscribeRequest;
import com.artinus.api.domain.subscribe.dto.request.UnsubscribeRequest;
import com.artinus.api.domain.subscribe.dto.response.SubscribeIdResponse;
import com.artinus.api.domain.subscribe.dto.response.SubscribeLogResponse;
import com.artinus.api.domain.subscribe.service.ChannelSubscribeFacade;
import com.artinus.api.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ChannelSubscribeController {

    private final ChannelSubscribeFacade channelSubscribeFacade;

    @PostMapping("/v1/channels/subscribe")
    public ApiResponse<SubscribeIdResponse> subscribe(@RequestBody SubscribeRequest subscribeRequest) {
        SubscribeIdResponse response = channelSubscribeFacade.subscribe(subscribeRequest);
        return ApiResponse.success(response);
    }

    @PostMapping("/v1/channels/unsubscribe")
    public ApiResponse<SubscribeIdResponse> unsubscribe(@RequestBody UnsubscribeRequest unsubscribeRequest) {
        SubscribeIdResponse response = channelSubscribeFacade.unsubscribe(unsubscribeRequest);
        return ApiResponse.success(response);
    }
}
