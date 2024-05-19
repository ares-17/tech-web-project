<script setup lang="ts">
import { RouterView } from 'vue-router'
import Navbar  from '@/components/Navbar.vue'
</script>

<template>
  <header>
    <Navbar></Navbar>
  </header>

  <main class="page-content content-wrapper">
    <div ><RouterView /></div>
  </main>
</template>

<style scoped>

</style>
