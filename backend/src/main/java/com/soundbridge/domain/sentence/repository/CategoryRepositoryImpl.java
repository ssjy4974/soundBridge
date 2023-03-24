package com.soundbridge.domain.sentence.repository;

import static com.soundbridge.domain.sentence.entity.QCategory.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.sentence.entity.Category;
import com.soundbridge.domain.sentence.response.CategoryRes;
import com.soundbridge.domain.sentence.response.QCategoryRes;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CategoryRes> findByCategoriesByMemberId(Long memberId) {
        return jpaQueryFactory.select(
                new QCategoryRes(
                    category.id.as("mySentenceLogId"),
                    category.categoryName.as("category")
                )
            )
            .from(category)
            .innerJoin(category.member)
            .where(category.member.id.eq(memberId))
            .orderBy(category.categoryCount.desc())
            .fetch();
    }

    @Override
    public Optional<Category> findCategory(String categoryName, Long memberId) {
        return Optional.ofNullable(
            jpaQueryFactory.selectFrom(category)
                .where(category.categoryName.eq(categoryName),
                    category.member.id.eq(memberId))
                .fetchOne());
    }

}
