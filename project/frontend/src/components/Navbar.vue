<template>
    <v-app-bar>
      <v-app-bar-title>
        <router-link class="nav-link m-3" to="/">SmartQuiz</router-link>
      </v-app-bar-title>
      <v-spacer></v-spacer>
      <template v-slot:append>
        <router-link class="nav-link m-3 d-none d-md-flex" to="/login">{{ $t('navbar_signin') }}</router-link>
        <router-link class="nav-link m-3 d-none d-md-flex" to="/register">{{ $t('navbar_signup') }}</router-link>
        <v-btn icon class="d-flex d-md-none" @click="drawer = true">
          <v-icon>mdi-menu</v-icon>
        </v-btn>
      </template>
    </v-app-bar>

    <v-navigation-drawer
      v-model="drawer"
      app
      temporary
      class="d-md-none"
    >
      <v-list>
        <v-list-item>
          <router-link class="nav-link m-3" to="/login">{{ $t('navbar_signin') }}</router-link>
        </v-list-item>
        <v-list-item>
          <router-link class="nav-link m-3" to="/register">{{ $t('navbar_signup') }}</router-link>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';
import { RouterLink } from 'vue-router';

export default defineComponent({
    name: 'Navbar',
    components: {
        RouterLink
    },
    setup(){
        const drawer = ref(false);
        return {
            drawer
        }
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

.nav-link{
  font-weight: 600;
}

.nav-link:hover{
  text-decoration: underline;
}
.v-app-bar-title{
  font-weight: 500;
  font-size: x-large;
  text-transform: uppercase;
}
.v-app-bar.v-toolbar{
    background: #0000001f;
    color: white;
}


</style>