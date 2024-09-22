package com.artinus.api.domain.member.service;

import com.artinus.api.IntegrationTestSupport;
import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.repository.MemberRepository;
import com.artinus.api.domain.member.value.PhoneNumber;
import com.artinus.api.global.exception.ArtinusAppException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
class MemberServiceTest extends IntegrationTestSupport {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    
    @DisplayName("핸드폰 번호로 회원을 조회한다.")
    @Test
    void findByPhoneNumber() {
        // given
        PhoneNumber phoneNumber = new PhoneNumber("01011112222");
        Member member = Member.builder()
                .phoneNumber(phoneNumber)
                .build();
        memberRepository.save(member);

        // when
        Member findMember = memberService.findByPhoneNumber(phoneNumber);

        // then
        Assertions.assertThat(findMember.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @DisplayName("존재하지 않는 핸드폰 번호로 회원 조회 시 예외가 발생한다.")
    @Test
    void findByNonExistPhoneNumber() {
        // given
        PhoneNumber phoneNumber = new PhoneNumber("01011112222");

        // when, then
        assertThatThrownBy(() -> memberService.findByPhoneNumber(phoneNumber))
                .isInstanceOf(ArtinusAppException.class)
                .hasMessage("not found entity");
    }
}