package com.artinus.api.domain.channel.entity;

import com.artinus.api.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
}
