package com.soundbridge.domain.sentence.entity;

import com.soundbridge.domain.member.entity.Member;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "INT UNSIGNED")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "fk_category_member_idx"))
    private Member member;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    List<QuickSentence> quickSentenceList;

    @Column(length = 10, nullable = false)
    private String categoryName;

    @Column(columnDefinition = "INT UNSIGNED")
    @ColumnDefault("0")
    private int categoryCount;

    @Builder
    public Category(Long categoryId, Member member, String categoryName, int categoryCount) {
        this.id = categoryId;
        this.member = member;
        this.categoryName = categoryName;
        this.categoryCount = categoryCount;
    }

    public void updateCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void increaseCount() {
        this.categoryCount++;
    }

}
