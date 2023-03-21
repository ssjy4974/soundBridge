package com.soundbridge.domain.voice.repository;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;
import static com.soundbridge.domain.voice.entity.QFeature.feature;
import static com.soundbridge.domain.voice.entity.QVoice.voice;
import static com.soundbridge.domain.voice.entity.QVoiceFeature.voiceFeature;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.voice.request.VoiceListConditionReq;
import com.soundbridge.domain.voice.response.QFeatureRes;
import com.soundbridge.domain.voice.response.QVoiceDetailRes;
import com.soundbridge.domain.voice.response.VoiceDetailRes;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@Slf4j
@RequiredArgsConstructor
public class VoiceRepositoryImpl implements VoiceRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<VoiceDetailRes> findAllVoiceWithPaging(Pageable pageable, Long cursorId,
        VoiceListConditionReq voiceCondReq) {
//        final List<VoiceDetailRes> fetch = jpaQueryFactory.select(
//                Projections.fields(VoiceDetailRes.class,
//                    voiceFeature.voice.id.as("voiceId"),
//                    voiceFeature.voice.voiceAge.as("age"),
//                    voiceFeature.voice.modelUrl.as("modelUrl"),
//                    voiceFeature.voice.voiceGender.as("voiceGender"),
//                    voiceFeature.voice.voiceName.as("voiceName"),
//                    voiceFeature.voice.member.id.as("memberId")
//                ))
//            .from(voiceFeature)
//            .innerJoin(voiceFeature.voice, voice)
//            .innerJoin(voiceFeature.feature,feature)
//            .join(voiceFeature)
//            .where(voiceAge(voiceCondReq.getVoiceAge()),
//                voiceGender(voiceCondReq.getVoiceGender()),
//                cursorId(cursorId)
//                    ,(voiceFeatures(voiceCondReq.getFeatures())))
//            .limit(pageable.getPageSize() + 1)
//            .orderBy(voice.id.desc())
//            .fetch();
//
//        boolean hasNext = false;
//
//        if(fetch.size() == pageable.getPageSize() + 1) {
//            fetch.remove(pageable.getPageSize());
//            hasNext = true;
//        }
        List<VoiceDetailRes> transform = jpaQueryFactory.from(voiceFeature)
            .innerJoin(voiceFeature.voice, voice)
            .innerJoin(voiceFeature.feature, feature)
            .where(voiceAge(voiceCondReq.getVoiceAge()),
                voiceGender(voiceCondReq.getVoiceGender()),
                voiceFeature.voice.id.in(
                    JPAExpressions
                        .select(voice.id)
                        .from(voice, voiceFeature)
                        .where(
                            voice.id.eq(voiceFeature.voice.id),
                            voiceFeatures(voiceCondReq.getFeatures()))
                )
            )
            .transform(groupBy(voice.id)
                .list(
                    new QVoiceDetailRes(
                        voice.id.as("voiceId"),
                        voice.voiceAge.as("age"),
                        voice.modelUrl.as("modelUrl"),
                        voice.voiceGender.as("voiceGender"),
                        voice.voiceName.as("voiceName"),
                        voice.member.id.as("memberId"),
                        list(new QFeatureRes(
                            feature.id.as("featureId"),
                            feature.featureName.as("featureName")
                        )))));
//        )
//        new SliceImpl<>(fetch, pageable, hasNext)

        boolean hasNext = false;

        if(transform.size() == pageable.getPageSize() + 1) {
            transform.remove(pageable.getPageSize());
            hasNext = true;
        }

        log.info(transform.toString());
        return new SliceImpl<>(transform, pageable, hasNext);
    }

//    voiceFeatures(voiceCondReq.getFeatures())
//        )
    private BooleanBuilder voiceFeatures(List<Long> voiceFeatures) {
        BooleanBuilder builder = new BooleanBuilder();

        if (voiceFeatures == null) {
            return null;
        }

        for (Long i : voiceFeatures) {
            if (i == null) {
                continue;
            }

            builder.or(voiceFeature.feature.id.eq(i));
        }

        return builder;
    }

    private BooleanExpression voiceAge(Integer voiceAge) {
        return voiceAge == null ? null : voice.voiceAge.eq(voiceAge);
    }

    private BooleanExpression voiceGender(String voiceGender) {
        return voiceGender == null ? null : voice.voiceGender.eq(voiceGender);
    }

    private BooleanExpression cursorId(Long cursorId) {
        return cursorId == null ? null : voice.id.lt(cursorId);
    }

}
