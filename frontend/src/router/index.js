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
import PronounciationBasics from "../views/PronounciationBasics.vue";
import PronounciationBasicsDetail from "../views/PronounciationBasicsDetail.vue";
import PronounciationWords from "../views/PronounciationWords.vue";
import PronounciationWordsDetail from "../views/PronounciationWordsDetail.vue";
import PronounciationSelection from "../views/PronounciationSelection.vue";

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
    path: "/mypage/",
    name: "mypagedisabled",
    component: MypageDisabled,
    // 자식 컴포넌트
    children: [
      { path: "freqUsedPhrase", component: FreqUsedPhrase },
      { path: "meetings", component: MyMeetings },
    ],
  },
  {
    path: "/mypage/:id",
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
    path: "/pronounciationbasics",
    name: "pronounciationbasics",
    component: PronounciationBasics,
    //detail child로 만들기
  },
  {
    path: "/pronounciationbasics",
    name: "pronounciationbasics",
    component: PronounciationBasics,
    //detail child로 만들기
  },
  {
    path: "/pronounciationwords",
    name: "pronounciationwords",
    component: PronounciationWords,
    //detail child로 만들기
  },
  {
    path: "/pronounciationselection",
    name: "pronounciationselection",
    component: PronounciationSelection,
  },

  {
    path: "/feedbackMeeting/:meetingId",
    name: "feedbackMeeting",
    component: FeedbackMeeting,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
