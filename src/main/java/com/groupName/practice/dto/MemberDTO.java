package com.groupName.practice.dto;

import com.groupName.practice.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private Long id;
    private String uid;
    private String password;
    private String email;

    // MemberDTO => MemberEntity
    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setUid(memberEntity.getUid());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setEmail(memberEntity.getEmail());
        return memberDTO;
    }
}
