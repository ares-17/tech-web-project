<template>
        <div class="row">
            <div class="title-col col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h1 class="title mt-3">
                    Divertiti creando quiz
                    <br>
                    con i tuoi amici!
                </h1>
            </div>
            <div class="input-col col-10 col-sm-10 col-md-10 col-lg-6 col-xl-6 mt-3">
                <div class="input-container bg-transparent w-100">
                    <div class="row">
                        <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-6 mb-2 h-100">
                            <v-text-field :label="$t('logToQuiz_title')" 
                                variant="solo-filled" class=""
                                v-model="code" 
                                append-inner-icon='mdi-send'
                                required 
                                hide-details="auto" 
                                :rules="[validateUUID]"
                                @click:append-inner="getQuizByCode"
                            ></v-text-field>
                            <div class="w-100 d-flex justify-content-center">
                                <span class="text-center my-auto mx-auto or">oppure</span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-6 mb-3 my-3">
                            <v-btn size="large" block @click="toNewQuiz">
                                {{ $t('home_create_new_quiz') }}
                                <v-dialog
                                    v-model="dialogLogin"
                                    width="auto">
                                    <template v-slot:default="{ isActive }">
                                        <v-card
                                            max-width="500"
                                            prepend-icon="mdi-alert"
                                            :text="$t('create_quiz_dialog_text_homepage')"
                                            :title="$t('create_quiz_dialog_title_homepage')">
                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                            <v-btn :text="$t('create_quiz_dialog_close_btn')" 
                                                @click="() => isActive.value = false"></v-btn>
                                            <v-btn :text="$t('create_quiz_dialog_ok_btn')" 
                                                @click="goToLogin"></v-btn>
                                        </v-card-actions>
                                        </v-card>
                                    </template>
                                </v-dialog>
                            </v-btn>
                        </div>
                    </div>
                </div>
            </div>
        </div>

</template>

<script lang="ts">
import type { QuizApi } from '@/api/QuizApi';
import HelloComponent from '@/components/HelloComponent.vue';
import { useSessionStore } from '@/stores/sessionStore';
import type { Ref } from 'vue';
import { inject, onBeforeUnmount, ref } from 'vue';
import { useRouter, type Router } from 'vue-router';

export default {
    name: 'HomeView',
    setup() {
        const code: Ref<string | undefined> = ref();
        const quizApi = inject('QuizApi') as QuizApi;
        const router: Router = useRouter();
        const uuidRegex = /^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$/i;
        const sessionStore = useSessionStore();
        const dialogLogin = ref(false);
        const isLogged = ref(false);
        const subscription = sessionStore.isLoggedSubject.subscribe(val => isLogged.value = val);

        function getQuizByCode() {
            if (!code.value || !uuidRegex.test(code.value)) {
                return;
            }
            quizApi.getQuizById({ uidQuiz: code.value })
                .then(() => {
                    router.push({ name: 'quiz-istance', params: { code: code.value } })
                })
                .catch(() => {});
        }

        function toNewQuiz(){
            if(!isLogged.value){
                dialogLogin.value = true;
                return;
            }
            router.push({ name: 'create-quiz' });
        }

        function validateUUID(value: string){
            return uuidRegex.test(value) || 'Formato del codice errato';
        }

        function goToLogin(){
            router.push({ name: 'login' });
        }

        onBeforeUnmount(() => subscription.unsubscribe());

        return {
            code,
            getQuizByCode,
            toNewQuiz,
            validateUUID,
            goToLogin,
            dialogLogin
        }
    },
    component: {
        HelloComponent
    }
};
</script>

<style scoped>
@media (min-width: 992px) { 
    .title-col{
        margin-left: 5rem;
    }

    .input-col{
        margin-left: 5rem;
    }

    h1.title{
        margin-top: 2rem;
        font-size: 4rem;
        color: white;
        font-weight: 600;
        font-family: Poppins;
        text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
    }
}

@media (max-width: 992px){
    .title-col{
        display: flex;
        justify-content: center;
        align-items: center;
        margin-left: 1rem;
    }

    .input-col{
        display: flex;
        justify-content: center;
        align-items: center;
        margin: auto;
    }

    h1.title{
        font-size: xx-large;
        color: white;
        font-weight: 600;
        font-family: Poppins;
        text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
    }
}
span.or{
    color: white;
    text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}

</style>