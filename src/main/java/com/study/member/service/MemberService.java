package com.study.member.service;

import com.study.member.dto.MemberDTO;
import com.study.member.entity.MemberEntity;
import com.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service  //스프링 빈으로 등록함
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // repository의 save 메서드 호출 (entity 객체를 넘겨줘야함)
        // dto -> entity 로 변환

        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB 에서 조회
            2. DB 에서 조회한 비밀번호와 사용자가 입력한 비밀번호와 일치하는지 판단
         */

        // Optional로 한번 더 감싸진 느낌 (null 방지? )
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다 (해당 이메일을 가진 회원 정보가 있음)
            MemberEntity memberEntity = byMemberEmail.get(); // 이렇게 get()으로 Optional 감싼거 벗긴 느낌 ?..
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                // 비밀번호 일치
                // entity -> dto 변환 후 return
                // 변환타입은 dto 이다.
                // entity로는 service 안에서만 쓸거다 ~
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;

            }else{
                // 비밀번호 불일치
                return null;
            }

        }else{
            // 조회 결과가 없다
            return null;
        }

    }

    public List<MemberDTO> findAll() {
        //repository와 관련된건 거의 entity로 주고 받는다
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();

        // entityList 에서 하나씩 꺼내서 DTOList에 옮겨담아

        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }

        return memberDTOList;
    }
}
