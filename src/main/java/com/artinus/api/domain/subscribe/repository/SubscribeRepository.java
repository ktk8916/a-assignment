package com.artinus.api.domain.subscribe.repository;

import com.artinus.api.domain.channel.entity.Channel;
import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.subscribe.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    Optional<Subscribe> findByMemberAndChannel(Member member, Channel channel);
}
