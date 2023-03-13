package com.soundbridge.domain.pronunciation.repository;

import com.soundbridge.domain.pronunciation.entity.BasicLetter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicLetterRepository extends JpaRepository<BasicLetter, Long>, BasicLetterRepositorySupport {

}
