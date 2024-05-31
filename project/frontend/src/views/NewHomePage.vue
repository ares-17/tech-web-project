<template>
    <div class="container-fluid container-page">
        <div class="row">
            <div class="title-col col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                <h1 class="title mt-3">
                    Divertiti creando quiz
                    <br>
                    con i tuoi amici!
                </h1>
            </div>
            <div class="input-col col-12 col-sm-12 col-md-12 col-lg-3 col-xl-3 mt-3">
                <div class="input-container bg-transparent">
                    <div class="">
                        <label for="quizInput" class="form-label" style="color: white;">{{ $t('logToQuiz_title') }}</label>
                        <div class="row">
                            <div class="col-12 col-md-9 col-sm-9 col-lg-9 col-xl-9 mb-2 mx-auto my-auto">
                                <input type="text" class="form-control" id="quizInput" v-model="code">
                            </div>
                            <div class="col-12 col-md-3 col-sm-3 col-lg-3 mb-3 col-xl-3 mb-2 mx-auto my-auto">
                                <button class="btn btn-primary w-100" @click="getQuizByCode"><i
                                        class="bi bi-arrow-right"></i></button>
                            </div>
                        </div>
                        
                        <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-3 mx-auto my-3">
                            <button class="btn btn-primary w-100">{{ $t('home_create_new_quiz') }}</button>
                        </div>
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

        return {
            code,
            getQuizByCode,
            toastService
        }
    },
    component: {
        Toast,
        HelloComponent
    }
};
</script>

<style scoped>
img.img-carte {
    max-width: 18vw;
    height: auto;
}

.container-page {
    width: 100%;
    margin: 0;
    padding: 0;
    background-image: url('../assets/wallapper-poker.jpg');
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    height: 50rem;
    padding-top: 10rem;
}

@media (min-width: 1200px) {
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
        font-weight: unset;
        font-family: Poppins;
        text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
    }
}

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
        font-weight: unset;
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
    }

    h1.title{
        font-size: xx-large;
        color: white;
        font-weight: unset;
        font-family: Poppins;
        text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
    }
}


</style>