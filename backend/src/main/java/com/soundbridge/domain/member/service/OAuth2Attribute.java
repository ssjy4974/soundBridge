package com.soundbridge.domain.member.service;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.parameters.P;

@ToString
@Builder(access = AccessLevel.PRIVATE)
@Getter
class OAuth2Attribute {

    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String nickname;
    private String profile;
    private String gender;
    private int age;

    static OAuth2Attribute of(String provider, String attributeKey,
        Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return ofGoogle(attributeKey, attributes);
            case "kakao":
                return ofKakao(attributeKey, attributes);
            case "naver":
                return ofNaver(attributeKey, attributes);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuth2Attribute ofNaver(String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> naverAccount = (Map<String, Object>) attributes.get("response");

        int age = Integer.parseInt(((String)naverAccount.get("age")).split("-")[0]);

        return OAuth2Attribute.builder()
            .nickname((String) naverAccount.get("nickname"))
            .email((String) naverAccount.get("email"))
            .profile((String) naverAccount.get("profile_image"))
            .gender((String) naverAccount.get("gender"))
            .age(age)
            .attributes(naverAccount)
            .attributeKey(attributeKey)
            .build();
    }

    private static OAuth2Attribute ofGoogle(String attributeKey,
        Map<String, Object> attributes) {
        return OAuth2Attribute.builder()
            .nickname((String) attributes.get("name"))
            .email((String) attributes.get("email"))
            .profile((String) attributes.get("picture"))
            .gender((String) attributes.get(""))
            .attributes(attributes)
            .attributeKey(attributeKey)
            .build();
    }

    private static OAuth2Attribute ofKakao(String attributeKey,
        Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        int age = Integer.parseInt(((String)kakaoAccount.get("age_range")).split("~")[0]);
        String gender = (String) kakaoAccount.get("gender");

        if(gender.equals("male")) {
            gender = "M";
        } else {
            gender = "W";
        }

        return OAuth2Attribute.builder()
            .nickname((String) kakaoProfile.get("nickname"))
            .email((String) kakaoAccount.get("email"))
            .profile((String) kakaoProfile.get("profile_image_url"))
            .gender((String) kakaoAccount.get("gender"))
            .age(age)
            .attributes(kakaoAccount)
            .attributeKey(attributeKey)
            .build();
    }


    Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", attributeKey);
        map.put("nickname", nickname);
        map.put("email", email);
        map.put("profile", profile);
        map.put("gender", gender);
        map.put("age", age);

        return map;
    }
}