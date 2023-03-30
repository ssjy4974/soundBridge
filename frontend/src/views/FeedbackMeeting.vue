<template>
  <div>
    <div v-if="openviduInfo.session">
      <div id="video-container" class="sub-video">
        <user-video
          v-for="sub in openviduInfo.subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
        />
      </div>
      <div id="user-video" v-show="openviduInfo.subscribers.length === 0">
        <user-video :stream-manager="openviduInfo.publisher" />
      </div>
      <div id="my-video" v-show="openviduInfo.subscribers.length != 0">
        <my-video :stream-manager="openviduInfo.publisher" />
      </div>
    </div>
    <div class="button-section">
      <button id="doneBtn" @click="feedbackDone">상담 종료</button>

      <button id="exitBtn" @click="leaveSession">
        <i class="fa-solid fa-circle-xmark fa-3x"></i>
      </button>

      <button id="chatBtn" @click="openChatModal">
        <i class="fa-solid fa-message fa-3x"></i>
      </button>
    </div>
    <transition name="slide-fade">
      <chat-modal
        :msgList="msgList"
        v-if="chatModal"
        @closeChatModal="openChatModal"
        @sendMsg="appendMsg"
      />
    </transition>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { apiInstance } from "@/api/index";
import { onBeforeMount, ref } from "@vue/runtime-core";
import { OpenVidu } from "openvidu-browser";
import router from "@/router/index";
import UserVideo from "@/components/meeting/UserVideo.vue";
import MyVideo from "@/components/meeting/MyVideo.vue";
import ChatModal from "@/components/meeting/ChatModal.vue";
import { useMember } from "@/store/Member";

const route = useRoute();
const api = apiInstance();
const memberStore = useMember();
const { accessToken } = memberStore;

const openviduInfo = ref({
  // OpenVidu objects
  OV: undefined,
  session: undefined,
  mainStreamManager: undefined,
  publisher: undefined,
  subscribers: [],
});
const msgList = ref([]);
const chatModal = ref(false);

onBeforeMount(() => {
  api
    .get(`/api/meetings/rooms/${route.params.meetingId}`)
    .then((res) => {
      openviduInfo.value.OV = new OpenVidu();
      // openviduInfo.value.OV.enableProdMode();
      openviduInfo.value.session = openviduInfo.value.OV.initSession();
      // On every new Stream received...
      console.log("streamCreate 전 ");
      openviduInfo.value.session.on("streamCreated", ({ stream }) => {
        const subscriber = openviduInfo.value.session.subscribe(stream);
        openviduInfo.value.subscribers.push(subscriber);
      });
      console.log("streamCreate 후");
      // On every Stream destroyed...
      openviduInfo.value.session.on("streamDestroyed", ({ stream }) => {
        const index = openviduInfo.value.subscribers.indexOf(
          stream.streamManager
        );
        if (index >= 0) {
          openviduInfo.value.subscribers.splice(index, 1);
        }
      });
      // On every asynchronous exception...
      openviduInfo.value.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      openviduInfo.value.session.on("signal:done", () => {
        alert("상담이 종료 되었습니다.");
        leaveSession();
      });
      openviduInfo.value.session.on("signal:chat", (event) => {
        if (
          event.from.connectionId ==
          openviduInfo.value.session.connection.connectionId
        ) {
          msgList.value.push({ text: event.data, side: "right" });
        } else {
          msgList.value.push({ text: event.data, side: "left" });
        }
      });
      openviduInfo.value.session
        .connect(res.data, {
          clientData: "test",
        })
        .then(() => {
          console.log("session connect 안에 들어옴");
          let publisher = openviduInfo.value.OV.initPublisher(undefined, {
            audioSource: undefined,
            videoSource: undefined,
            publishAudio: true,
            publishVideo: true,
            resolution: "640x1080",
            frameRate: 30,
            insertMode: "APPEND",
            mirror: true,
          });
          openviduInfo.value.mainStreamManager = publisher;
          openviduInfo.value.publisher = publisher;
          openviduInfo.value.session.publish(openviduInfo.value.publisher);
        })
        .catch((error) => {
          console.log(
            "There was an error connecting to the session:",
            error.code,
            error.message
          );
        });
      window.addEventListener("beforeunload", leaveSession);
    })
    .catch((err) => {
      err;
      router.replace("/");
    });
});

const leaveSession = () => {
  // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
  if (openviduInfo.value.session) {
    openviduInfo.value.session.disconnect();
    openviduInfo.value.session = undefined;
    openviduInfo.value.mainStreamManager = undefined;
    openviduInfo.value.publisher = undefined;
    openviduInfo.value.subscribers = [];
    openviduInfo.value.OV = undefined;
    openviduInfo.value = undefined;
  }

  // Remove beforeunload listener
  window.removeEventListener("beforeunload", leaveSession);
  router.replace("/");
};

const feedbackDone = () => {
  // 여기 방번호
  api
    .put(`/api/meetings/rooms/${route.params.meetingId}`)
    .then(() => {
      openviduInfo.value.session.signal({
        type: "done",
      });
    })
    .catch((err) => {
      err;
      alert("상담 종료 실패");
    });
};

const openChatModal = () => {
  chatModal.value = !chatModal.value;
};

const appendMsg = (text) => {
  openviduInfo.value.session.signal({
    type: "chat",
    data: text,
  });
};
</script>

<style scoped>
.button-section {
  position: absolute;
  bottom: 56px;
  z-index: 10;
}

#exitBtn {
  margin-left: 5vh;
  background-color: transparent;
}

#chatBtn {
  margin-left: 5vh;
  background-color: transparent;
}

.slide-fade-enter-from {
  transform: translateY(500px);
}

.slide-fade-enter-active {
  transition: all 0.8s;
}
.slide-fade-enter-to {
  transform: translateY(0px);
}

.slide-fade-leave-active {
  transition: all 0.8s;
}
.slide-fade-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateY(500px);
}
</style>
