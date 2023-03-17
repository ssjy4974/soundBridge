package com.soundbridge.domain.voice.repository;

import com.soundbridge.domain.voice.entity.Voice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceRepository extends JpaRepository<Voice, Long>, VoiceRepositorySupport {

}
