package com.soundbridge.domain.voice.repository;

import com.soundbridge.domain.voice.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

}
