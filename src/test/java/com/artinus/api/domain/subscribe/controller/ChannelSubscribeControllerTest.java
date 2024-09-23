package com.artinus.api.domain.subscribe.controller;

import com.artinus.api.ControllerTestSupport;
import com.artinus.api.domain.subscribe.dto.request.SubscribeRequest;
import com.artinus.api.domain.subscribe.dto.request.UnsubscribeRequest;
import com.artinus.api.domain.subscribe.dto.response.SubscribeIdResponse;
import com.artinus.api.domain.subscribe.entity.SubscribePlan;
import com.artinus.api.domain.subscribe.service.ChannelSubscribeFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ChannelSubscribeController.class)
class ChannelSubscribeControllerTest extends ControllerTestSupport {

    @MockBean
    private ChannelSubscribeFacade channelSubscribeFacade;

    @DisplayName("채널을 구독한다.")
    @Test
    void subscribe() throws Exception {
        //given
        SubscribeRequest request = SubscribeRequest.builder()
                .phoneNumber("01011112222")
                .channelId(1L)
                .plan(SubscribePlan.PREMIUM)
                .build();

        when(channelSubscribeFacade.subscribe(request))
                .thenReturn(new SubscribeIdResponse(1L));

        //when
        ResultActions perform = mockMvc.perform(
                post("/api/v1/channels/subscribe")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        perform.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").exists())
                .andExpect(jsonPath("$.time").exists());
    }

    @DisplayName("채널의 구독을 해지한다.")
    @Test
    void createOrder() throws Exception {
        //given
        UnsubscribeRequest request = UnsubscribeRequest.builder()
                .phoneNumber("01011112222")
                .channelId(1L)
                .plan(SubscribePlan.PREMIUM)
                .build();

        when(channelSubscribeFacade.unsubscribe(request))
                .thenReturn(new SubscribeIdResponse(1L));

        //when
        ResultActions perform = mockMvc.perform(
                post("/api/v1/channels/unsubscribe")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        perform.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").exists())
                .andExpect(jsonPath("$.time").exists());
    }
}