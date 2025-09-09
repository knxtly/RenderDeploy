package com.groupName.practice.entity;

import com.groupName.practice.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customer") // database에 "user" table 생성
public class MemberEntity { // table 역할
    // jpa => database를 객체처럼 사용가능

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true, nullable = false, length=50)
    private String uid;

    @Column(nullable = false, length = 30)
    private String password;
    @Column
    private String email;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setUid(memberDTO.getUid());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setEmail(memberDTO.getEmail());
        return memberEntity;
    }
}
