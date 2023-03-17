package com.soundbridge.domain.voice.repository;

import static com.soundbridge.domain.voice.entity.QVoice.voice;
import static com.soundbridge.domain.voice.entity.QVoiceFeature.voiceFeature;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soundbridge.domain.voice.request.VoiceListConditionReq;
import com.soundbridge.domain.voice.response.VoiceDetailRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.security.core.parameters.P;

@RequiredArgsConstructor
public class VoiceRepositoryImpl implements VoiceRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<VoiceDetailRes> findAllVoiceWithPaging(Pageable pageable, Long cursorId,
        VoiceListConditionReq voiceCondReq) {

        final List<VoiceDetailRes> fetch = jpaQueryFactory.select(
                Projections.fields(VoiceDetailRes.class,
                    voice.id.as("voiceId"),
                    voice.voiceAge.as("age"),
                    voice.modelUrl.as("modelUrl"),
                    voice.voiceGender.as("voiceGender"),
                    voice.voiceName.as("voiceName"),
                    voice.member.id.as("memberId")
                ))
            .from(voice)
            .where(voiceAge(voiceCondReq.getVoiceAge()),
                voiceGender(voiceCondReq.getVoiceGender()),
                voiceFeatures(voiceCondReq.getFeatures()), cursorId(cursorId))
            .limit(pageable.getPageSize() + 1)
            .orderBy(voice.id.desc())
            .fetch();

        boolean hasNext = false;

        if(fetch.size() == pageable.getPageSize() + 1) {
            fetch.remove(pageable.getPageSize());
            hasNext = true;
        }

        return  new SliceImpl<>(fetch, pageable, hasNext);
    }

    private BooleanBuilder voiceFeatures(List<Integer> voiceFeatures) {
        BooleanBuilder builder = new BooleanBuilder();

        if(voiceFeatures == null) {
            return null;
        }

        for (Integer i : voiceFeatures) {
            if (i == null) {
                continue;
            }

            builder.and(voiceFeature.feature.id.eq(Long.valueOf(i)));
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
