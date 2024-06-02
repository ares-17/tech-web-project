<template>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top">
        <div class="container-fluid pb-2 mx-3">
            <router-link class="navbar-brand" to="/">
                <img width="120" height="120" class="logo" src="/src/assets/logo.png" alt="external-help-website-development-xnimrodx-lineal-color-xnimrodx"/>   
            </router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <router-link class="nav-link" to="/create-quiz">{{ $t('navbar_createquizz') }}</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link me-2" to="/register">{{ $t('navbar_signup') }}</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" to="/login">{{ $t('navbar_signin') }}</router-link>
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
.navbar-nav .nav-link:hover {
    text-decoration: underline;
}
.navbar-nav .nav-link {
    color: white;
    font-size: medium;
    font-weight: 600;
    font-stretch: expanded;
    line-height: 1.25rem;
}

.logo{
    border-radius: 50%;
}
</style>