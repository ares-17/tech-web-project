<script setup lang="ts">
import { ref } from 'vue';
import { RouterView, useRouter } from 'vue-router';
import Navbar from './components/Navbar.vue';
import LoaderComponent from './components/LoaderComponent.vue';
import ErrorSnackbar from './components/ErrorSnackbar.vue';
import FooterComponent from './components/FooterComponent.vue';

const router = useRouter();
const isInHomePage = ref(false);

router.beforeEach((to, _) => {
  isInHomePage.value = to.path === '/';
})
</script>

<template>
  <v-layout ref="imageBackground" :class="{ 'image-background': isInHomePage }">
    <Navbar />
    <v-main class="page-content pt-0">
      <div class="container-fluid container-page">
        <router-view :key="$route.path"/>
      </div>
    </v-main>
    <FooterComponent />
  </v-layout>
  
  <LoaderComponent />
  <ErrorSnackbar />
</template>

<style scoped>
.v-layout {
  background-color: #6fa7e2;
  margin: 0;
  width: 100%;
  min-height: fit-content;
  flex-direction: column;
}

.container-page {
  width: 100%;
  margin: 0;
  padding: 0;
  padding-top: 10rem;
  padding-bottom: 20rem;
}

.image-background {
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  background-image: url('./assets/mystery-box-with-gifts-concept.jpg');
  height: 100vh;
}
</style>
