package com.artinus.api.domain.subscribe.controller;

import com.artinus.api.domain.subscribe.dto.request.SubscribeRequest;
import com.artinus.api.domain.subscribe.dto.request.UnsubscribeRequest;
import com.artinus.api.domain.subscribe.dto.response.SubscribeIdResponse;
import com.artinus.api.domain.subscribe.service.ChannelSubscribeFacade;
import com.artinus.api.global.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChannelSubscribeController {

    private final ChannelSubscribeFacade channelSubscribeFacade;

    @PostMapping("/v1/channels/subscribe")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<SubscribeIdResponse> subscribe(@RequestBody @Valid SubscribeRequest subscribeRequest) {
        SubscribeIdResponse response = channelSubscribeFacade.subscribe(subscribeRequest);
        return ApiResponse.success(response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/v1/channels/unsubscribe")
    public ApiResponse<SubscribeIdResponse> unsubscribe(@RequestBody @Valid UnsubscribeRequest unsubscribeRequest) {
        SubscribeIdResponse response = channelSubscribeFacade.unsubscribe(unsubscribeRequest);
        return ApiResponse.success(response);
    }
}
