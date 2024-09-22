package com.artinus.api.domain.member.repository;

import com.artinus.api.domain.member.entity.Member;
import com.artinus.api.domain.member.value.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByPhoneNumber(PhoneNumber phoneNumber);
}
