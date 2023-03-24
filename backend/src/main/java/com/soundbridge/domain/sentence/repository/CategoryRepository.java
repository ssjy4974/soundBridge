package com.soundbridge.domain.sentence.repository;

import com.soundbridge.domain.sentence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>,
    CategoryRepositorySupport {

}
