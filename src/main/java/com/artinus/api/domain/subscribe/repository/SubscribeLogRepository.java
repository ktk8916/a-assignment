package com.artinus.api.domain.subscribe.repository;

import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscribeLogRepository extends JpaRepository<SubscribeLog, Long> {
    List<SubscribeLog> findBySubscribeId(long id);
    @Query("select s " +
            "from SubscribeLog as s " +
            "inner join Member m " +
            "on s.member = m " +
            "and m.phoneNumber = :phoneNumber")
    List<SubscribeLog> findByPhoneNumber(PhoneNumber phoneNumber);
    @Query("select s " +
            "from SubscribeLog as s " +
            "where s.channel.id = :channelId " +
            "and s.createdAt BETWEEN :startDateTime AND :endDateTime")
    List<SubscribeLog> findByChannelBy(long channelId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
