package com.artinus.api.domain.member.service;

import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.repository.MemberRepository;
import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.global.exception.ArtinusAppException;
import com.artinus.api.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.artinus.api.global.exception.ExceptionCode.NOT_FOUND_ENTITY;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findByPhoneNumber(PhoneNumber phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ArtinusAppException(NOT_FOUND_ENTITY));
    }
}
