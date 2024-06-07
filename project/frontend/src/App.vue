<script setup lang="ts">
import { ref } from 'vue';
import { RouterView, useRouter } from 'vue-router';
import Navbar from './components/Navbar.vue';

const router = useRouter();
const isInHomePage = ref(false);

router.beforeEach((to, _) => {
  isInHomePage.value = to.path === '/'
})
</script>

<template>
  <v-layout :class="{ 'image-background': isInHomePage }">
    <Navbar /> 
    <v-main class="page-content content-wrapper">
        <div class="container-fluid container-page" >
          <router-view />
        </div>
    </v-main>
  </v-layout>
</template>

<style scoped>
.v-layout {
  background-color:  #6fa7e2; /* Tonalità di blu */
  margin: 0;
  width: 100%;
  min-height: fit-content;
}

.container-page{
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
  background-image: url('src/assets/mystery-box-with-gifts-concept.jpg');
}

</style>
