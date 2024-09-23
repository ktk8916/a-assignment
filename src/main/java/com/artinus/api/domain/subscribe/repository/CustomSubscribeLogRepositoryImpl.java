package com.artinus.api.domain.subscribe.repository;

import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.artinus.api.domain.channel.entity.QChannel.channel;
import static com.artinus.api.domain.member.entity.QMember.member;
import static com.artinus.api.domain.subscribe.entity.QSubscribeLog.subscribeLog;

@RequiredArgsConstructor
public class CustomSubscribeLogRepositoryImpl implements CustomSubscribeLogRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SubscribeLog> findBySubscribeId(long subscribeId) {
        return jpaQueryFactory.select(subscribeLog)
                .from(subscribeLog)
                .where(subscribeLog.subscribe.id.eq(subscribeId))
                .fetch();
    }

    @Override
    public List<SubscribeLog> findByPhoneNumber(PhoneNumber phoneNumber) {
        return jpaQueryFactory.select(subscribeLog)
                .from(subscribeLog)
                .innerJoin(subscribeLog.member, member)
                .where(member.phoneNumber.eq(phoneNumber))
                .fetch();
    }

    @Override
    public List<SubscribeLog> findByChannelAndCreatedDay(long channelId, LocalDate createdDay) {
        return jpaQueryFactory.select(subscribeLog)
                .from(subscribeLog)
                .innerJoin(subscribeLog.channel, channel)
                .where(
                        channel.id.eq(channelId),
                        channel.createdAt.goe(createdDay.atStartOfDay()),
                        channel.createdAt.lt(createdDay.atStartOfDay().plusDays(1)))
                .fetch();
    }

}
