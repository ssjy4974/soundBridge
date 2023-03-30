import { createRouter, createWebHistory } from "vue-router";
// 회원 페이지
import Login from "../views/Login.vue";
import LoginSuccess from "../components/LoginSuccess.vue";
import SelectRole from "../components/SelectRole.vue";
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
import RecordState from "../components/mypage/RecordState.vue";
import VoiceRecord from "../views/VoiceRecord.vue";

// 마이 페이지 상담 조회
import MyMeetings from "../components/mypage/MyMeetings.vue";
import FreqUsedPhrase from "../components/pronounce/FreqUsedPhrase.vue";

import { useMember } from "@/store/Member";

const routes = [
  // 회원 경로
  {
    path: "/",
    name: "login",
    component: Login,
  },
  {
    path: "/login-success",
    name: "loginSuccess",
    component: LoginSuccess,
  },
  //봉사자, 청각장애인 선택 페이지
  {
    path: "/select-role",
    name: "role",
    component: SelectRole,
  },
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
    children: [
      { path: "recordState", component: RecordState },
      { path: "meetings", component: MyMeetings },
    ],
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

import { storeToRefs } from "pinia";
import { ref } from "vue";
router.beforeEach(async (to, from, next) => {
  // console.log("@#@ ", from.fullPath);
  // if (from.fullPath === "/" && !localStorage.getItem("isLogin")) {
  //   console.log("!@!@!");
  //   localStorage.removeItem("isLogin");
  //   next();

  //   return;
  // }

  // const memberStore = useMember();

  // // alert("뭐냐");
  // const { member, accessToken } = storeToRefs(memberStore);

  // if (accessToken.value === null || accessToken.value === "") {
  //   console.log("is Login !");
  //   await useMember().refreshAccessToken();

  //   await useMember().setMemberInfo();
  // }

  // if (accessToken != "" && member.memberId === 0) {
  //   memberStore.setMemberInfo();
  // }

  // if (accessToken === null && accessToken === "") {
  //   alert("다시 로그인 해주세요!");
  //   useMember().clearLogin();
  //   next({
  //     path: "/login",
  //     query: { redirect: to.fullPath },
  //   });
  // }

  // useMember().checkLogin();
  next();
});

export default router;
