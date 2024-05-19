<template>
    <nav class="navbar navbar-expand-lg navbar-light bg-secondary-subtle fixed-top">
        <div class="container">
            <router-link class="navbar-brand" to="/">
                <img width="40" height="40" src="https://img.icons8.com/external-xnimrodx-lineal-color-xnimrodx/64/external-help-website-development-xnimrodx-lineal-color-xnimrodx.png" alt="external-help-website-development-xnimrodx-lineal-color-xnimrodx"/>   
            </router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <router-link class="nav-link" to="/create-quiz">{{ $t('navbar_createquizz') }}</router-link>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <router-link class="btn btn-outline-primary me-2" to="/register">{{ $t('navbar_signup') }}</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="btn btn-primary" to="/login">{{ $t('navbar_signin') }}</router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { RouterLink } from 'vue-router';

export default defineComponent({
    name: 'Navbar',
    components: {
        RouterLink
    }
});
</script>

<style scoped>
/* Stili personalizzati */
.navbar-nav .nav-link {
    color: #000;
}
</style>