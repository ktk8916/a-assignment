package com.artinus.api.domain.channel.sevice;

import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.channel.repository.ChannelRepository;
import com.artinus.api.global.exception.ArtinusAppException;
import com.artinus.api.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.artinus.api.global.exception.ExceptionCode.INVALID_SUBSCRIBE_PLAN;
import static com.artinus.api.global.exception.ExceptionCode.NOT_FOUND_ENTITY;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelService {

    private final ChannelRepository channelRepository;

    public Channel findSubscribeAbleById(long id) {
        Channel channel = findById(id);
        if (!channel.isSubscribeAble()) {
            throw new ArtinusAppException(INVALID_SUBSCRIBE_PLAN);
        }
        return channel;
    }

    public Channel findUnsubscribeAbleById(long id) {
        Channel channel = findById(id);
        if (!channel.isUnsubscribeAble()) {
            throw new ArtinusAppException(INVALID_SUBSCRIBE_PLAN);
        }
        return channel;
    }

    public Channel findById(long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new ArtinusAppException(NOT_FOUND_ENTITY));
    }
}
