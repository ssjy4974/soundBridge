<template>
  <div>
    <h1>Feedback Web RTC</h1>

    <div id="session" v-if="session">
      <div id="session-header">
        <h1 id="session-title">{{ mySessionId }}</h1>
        <input
          class="btn btn-large btn-danger"
          type="button"
          id="buttonLeaveSession"
          @click="leaveSession"
          value="Leave session"
        />
      </div>
      <div id="main-video" class="col-md-6">
        <user-video :stream-manager="mainStreamManager" />
      </div>
      <div id="video-container" class="col-md-6">
        <user-video :stream-manager="publisher" />
        <user-video
          v-for="sub in subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { apiInstance } from "@/api/index";
import { onBeforeMount, ref } from "@vue/runtime-core";
import { useRoute } from "vue-router";
import { OpenVidu } from "openvidu-browser";
import router from "@/router/index";

const api = apiInstance();
const route = useRoute();

const openviduInfo = ref({
  // OpenVidu objects
  OV: undefined,
  session: undefined,
  mainStreamManager: undefined,
  publisher: undefined,
  subscribers: [],
});

onBeforeMount(() => {
  api
    .post(`/api/meetings/rooms/${route.params.meetingId}`)
    .then((res) => {
      openviduInfo.value.OV = new OpenVidu();
      // openviduInfo.value.OV.enableProdMode();
      openviduInfo.value.session = openviduInfo.value.OV.initSession();
      // On every new Stream received...
      openviduInfo.value.session.on("streamCreated", ({ stream }) => {
        const subscriber = openviduInfo.value.session.subscribe(stream);
        openviduInfo.value.subscribers.push(subscriber);
      });
      // On every Stream destroyed...
      openviduInfo.value.session.on("streamDestroyed", ({ stream }) => {
        const index = openviduInfo.value.subscribers.indexOf(
          stream.streamManager,
          0
        );
        if (index >= 0) {
          openviduInfo.value.subscribers.splice(index, 1);
        }
      });
      // On every asynchronous exception...
      openviduInfo.value.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });
      openviduInfo.value.session
        .connect(res.data.token, {
          clientData: "test",
        })
        .then(() => {
          let publisher = openviduInfo.value.OV.initPublisher(undefined, {
            audioSource: undefined,
            videoSource: undefined,
            publishAudio: true,
            publishVideo: true,
            resolution: "640x480",
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
</script>

<style lang="scss" scoped></style>
