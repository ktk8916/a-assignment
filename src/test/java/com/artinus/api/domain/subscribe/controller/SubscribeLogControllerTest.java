package com.artinus.api.domain.subscribe.controller;

import com.artinus.api.ControllerTestSupport;
import com.artinus.api.domain.subscribe.service.SubscribeLogQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SubscribeLogController.class)
class SubscribeLogControllerTest extends ControllerTestSupport {

    @MockBean
    private SubscribeLogQueryService subscribeLogQueryService;

    @DisplayName("회원의 구독 이력을 조회한다.")
    @Test
    void getSubscribeLogsByMember() throws Exception {
        //given

        when(subscribeLogQueryService.getByPhoneNumber("01011112222"))
                .thenReturn(List.of());

        //when
        ResultActions perform = mockMvc.perform(
                get("/api/v1/subscribe/members/logs")
                        .queryParam("phoneNumber", "01011112222")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        perform.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.time").exists());
    }

    @DisplayName("채널의 날짜별 구독 이력을 조회한다.")
    @Test
    void getChannelSubscribeLogs() throws Exception {
        //given

        when(subscribeLogQueryService.getChannelLogs(1L, LocalDate.now()))
                .thenReturn(List.of());

        //when
        ResultActions perform = mockMvc.perform(
                get("/api/v1/subscribe/channels/{channelId}/logs", 1L)
                        .queryParam("phoneNumber", "01011112222")
                        .queryParam("date", "20240924")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        perform.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.time").exists());
    }
}