<template>
  <v-app-bar>
    <v-app-bar-title>
      <router-link class="nav-link m-3 d-none d-md-flex" to="/">SmartQuiz</router-link>
      <v-btn class="d-md-none" icon="mdi-home" variant="text" @click="router.push('/')"></v-btn>
    </v-app-bar-title>
    <v-spacer></v-spacer>
    <template v-slot:append>
      <div v-if="!isLogged" class="d-flex align-items-center">
        <RouterLink to="/login" class="nav-link m-3 d-none d-md-flex">{{ $t('navbar_signin') }}</RouterLink>
        <RouterLink to="/register" class="nav-link m-3 d-none d-md-flex">{{ $t('navbar_signup') }}</RouterLink>
        <v-btn icon class="d-flex d-md-none" @click="drawer = true">
          <v-icon>mdi-menu</v-icon>
        </v-btn>
      </div>
      <div v-else class="d-flex align-items-center">
        <span class="mx-5 d-none d-md-block">{{ $t('navbar_welcome', { username })  }}</span>
        <v-btn prepend-icon="mdi-exit-to-app" class="mx-3" variant="outlined" @click="logout">
        <span class="d-none d-md-block">Logout</span></v-btn>
      </div>
    </template>
  </v-app-bar>

  <v-navigation-drawer v-model="drawer" app temporary class="d-md-none">
    <v-list>
      <v-list-item>
        <RouterLink to="/login" class="nav-link m-3">{{ $t('navbar_signin') }}</RouterLink>
      </v-list-item>
      <v-list-item>
        <RouterLink to="/register" class="nav-link m-3">{{ $t('navbar_signup') }}</RouterLink>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>


</template>

<script lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import { RouterLink, useRouter } from 'vue-router';
import UserSignInDialog from '@/components/UserSignInDialog.vue';
import UserSignUpDialog from '@/components/UserSignUpDialog.vue';
import { useUserStore } from '@/stores/userStore';
import type { AuthenticationDto } from '@/api/models';
import router from '@/router';

export default defineComponent({
  name: 'Navbar',
  setup() {
    const drawer = ref(false);
    const userStore = useUserStore();
    const isLogged = ref(false);
    const username: Ref<string | undefined> = ref(undefined);
    const router = useRouter();

    const listener = (newVal?: AuthenticationDto, name?: string) => {
      isLogged.value = !!newVal && !!newVal.token;
      username.value = name;
    }

    userStore.addEventListener((newVal?: AuthenticationDto, name?: string) => {
      isLogged.value = !!newVal && !!newVal.token;
      username.value = name;
    });

    function logout(){
      userStore.removeEventListener(listener);
      isLogged.value = false;
      userStore.clean();
      router.push('/');
    }

    return {
      drawer,
      userStore,
      isLogged,
      logout,
      username,
      router
    }
  },
  components: {
    RouterLink,
    UserSignInDialog,
    UserSignUpDialog
  },
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

.logo {
  border-radius: 50%;
}

.nav-link {
  font-weight: 600;
}

.nav-link:hover {
  text-decoration: underline;
}

.v-app-bar-title {
  font-weight: 500;
  font-size: x-large;
  text-transform: uppercase;
}

.v-app-bar.v-toolbar {
  background: #0000001f;
  color: white;
}
</style>