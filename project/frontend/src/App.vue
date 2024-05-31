<script setup lang="ts">
import { RouterView } from 'vue-router'
import Navbar  from '@/components/Navbar.vue'
</script>

<template>
  <header>
    <Navbar></Navbar>
  </header>
  <main class="page-content content-wrapper">
    <div class="main-div"><router-view /></div>
  </main>
</template>

<style scoped>
div.main-div{
  position: absolute;
  top: 0vw;
  width: 100%;
}
</style>
