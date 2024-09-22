package com.artinus.api.domain.channel.entity;

import com.artinus.api.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Channel extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ChannelType type;

    public boolean isSubscribeAble() {
        return this.type.isSubscribeAble();
    }

    public boolean isUnsubscribeAble () {
        return this.type.isUnsubscribeAble();
    }

    @Builder
    private Channel(Long id, String name, ChannelType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
