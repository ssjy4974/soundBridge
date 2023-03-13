package com.soundbridge.domain.member.repository;

import com.soundbridge.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositorySupport {
    //CQRS 패턴 - 명령, 조회 책임 분리, Write가 있는 코드와 없는 코드를 분리해서 관리하기 위한 패턴
    //Support Repository 는 select 만
    //Command는 그냥 Repository에서 Support를 다시 상속 받아서 사용한다.'

    Optional<Member> findByEmail(String email);


}
