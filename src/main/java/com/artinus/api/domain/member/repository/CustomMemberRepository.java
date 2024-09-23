package com.artinus.api.domain.member.repository;

import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.value.PhoneNumber;

import java.util.Optional;

public interface CustomMemberRepository {

    Optional<Member> findByPhoneNumber(PhoneNumber phoneNumber);
}
