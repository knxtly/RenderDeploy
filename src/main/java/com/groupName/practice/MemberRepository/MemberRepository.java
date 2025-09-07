package com.groupName.practice.MemberRepository;

import com.groupName.practice.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 첫번째 인자 : 어떤 Entity인지, 두번째 인자 : pk 어떤 타입인지
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
/* JPA 기능들
save(entity) => 저장/수정
findById(id) => ID로 조회
findAll() => 전체 조회
delete(entity) => 삭제
count() => 개수 조회
 */
    // Spring Data JPA는 메서드 이름만 지어주면 쿼리를 자동으로 만들어 줍니다.
    // 규칙: findBy + 엔티티필드명 + 조건
    // Uid로 회원 찾기 (SELECT * FROM user WHERE uid = ?)
    Optional<MemberEntity> findByUid(String uid);
}
