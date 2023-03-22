package com.soundbridge.domain.pronunciation.repository;


import com.soundbridge.domain.pronunciation.entity.WordMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordMemberRepository extends JpaRepository<WordMember, Long>,
    WordMemberRepositorySupport {

}
