package com.artinus.api.domain.member.repository;

import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.value.PhoneNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

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
        Optional<Member> findMember = memberRepository.findByPhoneNumber(phoneNumber);

        // then
        assertThat(findMember).isPresent();
    }

    @DisplayName("핸드폰 번호로 회원을 조회한다. 존재하지 않으면 조회되지 않는다.")
    @Test
    void findByNonExistPhoneNumber() {
        // given
        PhoneNumber phoneNumber = new PhoneNumber("01011112222");

        // when
        Optional<Member> findMember = memberRepository.findByPhoneNumber(phoneNumber);

        // then
        assertThat(findMember).isEmpty();
    }
}