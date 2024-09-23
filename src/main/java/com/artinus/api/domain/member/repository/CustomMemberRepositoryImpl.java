package com.artinus.api.domain.member.repository;

import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.value.PhoneNumber;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.artinus.api.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Member> findByPhoneNumber(PhoneNumber phoneNumber) {
        Member findMember = jpaQueryFactory.select(member)
                .from(member)
                .where(member.phoneNumber.eq(phoneNumber))
                .fetchOne();
        return Optional.ofNullable(findMember);
    }
}
