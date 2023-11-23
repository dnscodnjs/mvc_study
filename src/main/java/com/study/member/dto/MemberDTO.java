package com.study.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자를 자동으로 만들어줌
@AllArgsConstructor // 필드를 모두 매개변수로 하는 생성자를 만들어줌
@ToString  // 필드값을 출력할때 toString 메서드를 자동으로 만들어줌
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
}
