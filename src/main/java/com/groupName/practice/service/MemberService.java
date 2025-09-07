package com.groupName.practice.service;

import com.groupName.practice.MemberRepository.MemberRepository;
import com.groupName.practice.dto.MemberDTO;
import com.groupName.practice.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // call save() in repository
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byUid = memberRepository.findByUid(memberDTO.getUid());

        if (byUid.isPresent()) {
            // Uid 조회 결과가 있다.
            MemberEntity memberEntity = byUid.get(); // Optional에서 꺼냄

            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                // correct password => Entity에서 DTO로 변환 후 return
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // incorrect password
                return null;
            }
        } else {
            // Uid 조회 결과가 없다.
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            // .get()으로 optional을 벗겨서 entity -> dto 변환
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
