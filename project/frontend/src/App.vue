<script setup lang="ts">
import { RouterView } from 'vue-router';
import Navbar from './components/Navbar.vue';
</script>

<template>
  <v-layout class="rounded rounded-md">
    <Navbar /> 
    <main class="page-content content-wrapper">
      <div class="main-div">
        <div class="container-fluid container-page">
          <router-view />
        </div>
      </div>
    </main>
  </v-layout>
</template>

<style scoped>
div.main-div {
  position: absolute;
  top: 0vw;
  width: 100%;
}

.container-page {
  width: 100%;
  margin: 0;
  padding: 0;
  background-image: url('./assets/wallpaper-quiz.jpg');
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  height: 120vw;
  padding-top: 10rem;
}

</style>
