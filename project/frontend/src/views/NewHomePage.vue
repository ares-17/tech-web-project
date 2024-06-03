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
                                @click:append-inner="getQuizByCode"
                            ></v-text-field>
                            <div class="w-100 d-flex justify-content-center">
                                <span class="text-center my-auto mx-auto or">oppure</span>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-6 mb-3 my-3">
                            <v-btn size="large" block @click="toNewQuiz">{{ $t('home_create_new_quiz') }}</v-btn>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <Toast ref="toastService" />

</template>

<script lang="ts">
import type { QuizApi } from '@/api/QuizApi';
import HelloComponent from '@/components/HelloComponent.vue';
import Toast from '@/components/Toast.vue';
import i18n from '@/i18n/i18n';
import type { Ref } from 'vue';
import { inject, ref } from 'vue';
import { useRouter, type Router } from 'vue-router';

export default {
    name: 'HomeView',
    setup() {
        const code: Ref<string | undefined> = ref();
        const quizApi = inject('QuizApi') as QuizApi;
        const toastService: Ref<typeof Toast | null> = ref(null);
        const router: Router = useRouter();

        function getQuizByCode() {
            if (!code.value) {
                return;
            }
            quizApi.getQuizById({ uidQuiz: code.value })
                .then(() => {
                    router.push({ name: 'quiz-istance', params: { code: code.value } })
                })
                .catch(() => toastService.value?.show(i18n.global.t('home_error_id')));
        }

        function toNewQuiz(){
            router.push({ name: 'create-quiz' });
        }

        return {
            code,
            getQuizByCode,
            toastService,
            toNewQuiz
        }
    },
    component: {
        Toast,
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