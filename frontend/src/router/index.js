import { createRouter, createWebHistory } from "vue-router";
// 회원 페이지
import Login from "../views/Login.vue";
import MypageDisabled from "../views/MypageDisabled.vue";
import MypageVolunteer from "../views/MypageVolunteer.vue";

//발화 페이지들
import Pronounce from "../views/Pronounce.vue";
import VoiceSelect from "@/views/VoiceSelect.vue";

// 게시판 관련 페이지들
import Feedback from "../views/Feedback.vue";
import FeedbackFilter from "../views/FeedbackFilter.vue";
import FeedbackMeeting from "../views/FeedbackMeeting.vue";

// 발음 교정 페이지들
import PracticeBasics from "../views/PracticeBasics.vue";
import PracticeBasicsDetail from "../views/PracticeBasicsDetail.vue";
import PracticeWords from "../views/PracticeWords.vue";
import PracticeWordsDetail from "../views/PracticeWordsDetail.vue";
import PracticeSelect from "../views/PracticeSelect.vue";

// 목소리 녹음
import VoiceRecord from "../views/VoiceRecord.vue";

// 마이 페이지 상담 조회
import MyMeetings from "../components/mypage/MyMeetings.vue";
import FreqUsedPhrase from "../components/pronounce/FreqUsedPhrase.vue";

const routes = [
  // 회원 경로
  {
    path: "/",
    name: "login",
    component: Login,
  },
  // 회원 유형별로 경로 다르게 어케함?
  {
    path: "/mypaged/",
    name: "mypagedisabled",
    component: MypageDisabled,
    // 자식 컴포넌트
    children: [
      { path: "freqUsedPhrase", component: FreqUsedPhrase },
      { path: "meetings", component: MyMeetings },
    ],
  },
  {
    path: "/mypagev",
    name: "mypagevolunteer",
    component: MypageVolunteer,
  },

  // 발화 경로
  {
    path: "/pronounce",
    name: "pronounce",
    component: Pronounce,
  },
  {
    path: "/voiceselect",
    name: "voiceselect",
    component: VoiceSelect,
  },

  // 게시판 경로
  {
    path: "/feedback",
    name: "feedback",
    component: Feedback,
  },
  // 발음 교정 경로들
  {
    path: "/practicebasics",
    name: "practicebasics",
    component: PracticeBasics,
  },
  {
    path: "/practicebasicsdetail/:basicLetterId",
    name: "praticebasicsdetail",
    component: PracticeBasicsDetail,
  },
  {
    path: "/practicewords",
    name: "practicewords",
    component: PracticeWords,
    //detail child로 만들기
  },
  {
    path: "/practiceselect",
    name: "practiceselect",
    component: PracticeSelect,
  },
  {
    path: "/practicebasics",
    name: "practicebasics",
    component: PracticeBasics,
  },
  {
    path: "/basicsdetail",
    name: "basicsdetail",
    component: PracticeBasicsDetail,
  },
  {
    path: "/practicewords",
    name: "practicewords",
    component: PracticeWords,
  },
  {
    path: "/wordsdetail",
    name: "wordsdetail",
    component: PracticeWordsDetail,
  },
  // Feedback Openvidu
  {
    path: "/feedbackMeeting/:meetingId",
    name: "feedbackMeeting",
    component: FeedbackMeeting,
  },
  //목소리 녹음 경로
  {
    path: "/record",
    name: "record",
    component: VoiceRecord,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
