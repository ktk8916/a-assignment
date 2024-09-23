package com.artinus.api.domain.subscribe.repository;

import com.artinus.api.domain.subscribe.entity.SubscribeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeLogRepository extends JpaRepository<SubscribeLog, Long>, CustomSubscribeLogRepository {

}
