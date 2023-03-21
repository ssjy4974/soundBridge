package com.soundbridge.domain.meeting.repository;

import com.soundbridge.global.error.ErrorCode;
import com.soundbridge.global.error.exception.NotFoundException;
import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MeetingRoomRepository {

    private OpenVidu openvidu;
    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;
    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    public void create(String code) throws OpenViduJavaClientException, OpenViduHttpException {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("customSessionId", code);

        SessionProperties properties = SessionProperties.fromJson(paramMap).build();
        openvidu.createSession(properties);

    }

    public String join(String code)
        throws OpenViduJavaClientException, OpenViduHttpException {
        //존재 하는 세션인지 확인
        Session session = findSessionByCode(code).orElseThrow(
            () -> new NotFoundException(ErrorCode.MEETING_ROOM_NOT_FOUND));
        log.info("OPENVIDU_URL = {}",OPENVIDU_URL);
        log.info("OPENVIDU_SECRET = {}",OPENVIDU_SECRET);
        //openvidu에 connection 요청
        ConnectionProperties properties = ConnectionProperties.fromJson(new HashMap<>()).build();
        Connection connection = session.createConnection(properties);

        return connection.getToken();
    }

    public Optional<Session> findSessionByCode(String code) {
        return Optional.ofNullable(openvidu.getActiveSession(code));
    }


}
