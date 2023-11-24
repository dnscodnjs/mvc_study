package com.study.member.repository;

import com.study.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // JpaRepository < 다룰 entity 클래스의 이름, entity 클래스의 PK 타입 > 으로 작성해야 한다.


    // 이메일로 회원 정보 조회 ( select * from member_table where member_email=?)
    // 위 쿼리를 아래의 메서드로 한 것
    // Optional = null 방지
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
