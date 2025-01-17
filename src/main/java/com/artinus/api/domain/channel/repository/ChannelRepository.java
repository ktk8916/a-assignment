package com.artinus.api.domain.channel.repository;

import com.artinus.api.domain.channel.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
