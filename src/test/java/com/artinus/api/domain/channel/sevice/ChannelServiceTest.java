package com.artinus.api.domain.channel.sevice;

import com.artinus.api.SpringBootTestSupport;
import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.channel.entity.ChannelType;
import com.artinus.api.domain.channel.repository.ChannelRepository;
import com.artinus.api.global.exception.ArtinusAppException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
class ChannelServiceTest extends SpringBootTestSupport {

    @Autowired
    private ChannelService channelService;
    @Autowired
    private ChannelRepository channelRepository;
    
    @DisplayName("채널을 id로 조회한다.")
    @Test
    void findById() {
        // given
        Channel channel = Channel.builder().build();
        Channel savedChannel = channelRepository.save(channel);

        // when
        Channel findChannel = channelService.findById(savedChannel.getId());

        // then
        assertThat(findChannel.getId()).isEqualTo(savedChannel.getId());
    }

    @DisplayName("존재하지 않는 id로 채널 조회 시 예외가 발생한다.")
    @Test
    void findByNonExistId() {
        // given

        // when, then
        assertThatThrownBy(() -> channelService.findById(999))
                .isInstanceOf(ArtinusAppException.class)
                .hasMessage("not found entity");
    }

    @DisplayName("구독 가능한 채널을 채널 id로 조회한다.")
    @Test
    void findSubscribeAbleById() {
        // given
        Channel channel = Channel.builder()
                .type(ChannelType.ONLY_SUBSCRIBE)
                .build();
        Channel savedChannel = channelRepository.save(channel);

        // when
        Channel subscribeAbleChannel = channelService.findSubscribeAbleById(savedChannel.getId());

        // then
        assertThat(subscribeAbleChannel.isSubscribeAble()).isTrue();
    }

    @DisplayName("조회된 채널이 구독 가능하지 않을 경우 예외가 발생한다.")
    @Test
    void findSubscribeAbleByIdNotSubscribable() {
        // given
        Channel channel = Channel.builder()
                .type(ChannelType.ONLY_UNSUBSCRIBE)
                .build();
        Channel savedChannel = channelRepository.save(channel);

        // when, then
        assertThatThrownBy(() -> channelService.findSubscribeAbleById(savedChannel.getId()))
                .isInstanceOf(ArtinusAppException.class)
                .hasMessage("invalid subscribe plan");
    }

    @DisplayName("구독 해지 가능한 채널을 채널 id로 조회한다.")
    @Test
    void findUnsubscribeAbleById() {
        // given
        Channel channel = Channel.builder()
                .type(ChannelType.ONLY_UNSUBSCRIBE)
                .build();
        Channel savedChannel = channelRepository.save(channel);

        // when
        Channel unsubscribeAbleChannel = channelService.findUnsubscribeAbleById(savedChannel.getId());

        // then
        assertThat(unsubscribeAbleChannel.isUnsubscribeAble()).isTrue();
    }

    @DisplayName("조회된 채널이 구독 해지 가능하지 않을 경우 예외가 발생한다.")
    @Test
    void findUnsubscribeAbleByIdNotUnsubscribable() {
        // given
        Channel channel = Channel.builder()
                .type(ChannelType.ONLY_SUBSCRIBE)
                .build();
        Channel savedChannel = channelRepository.save(channel);

        // when, then
        assertThatThrownBy(() -> channelService.findUnsubscribeAbleById(savedChannel.getId()))
                .isInstanceOf(ArtinusAppException.class)
                .hasMessage("invalid subscribe plan");
    }
}