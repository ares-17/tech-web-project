<template>
    <div class="container mt-5 pt-5">
        <div class="row mx-auto">
            <div class="col-12 col-xl-6 col-lg-6 mx-auto">
                <Stepper v-if="quiz && quiz.questions?.length" :stepsConfig="stepsConfig" ref="stepperRef">
                    <template v-for="(question, index) of quiz?.questions" v-slot:[`step-${index}`]>
                        <QuestionComponent 
                            :question="question"
                            :index="index"
                            :totalQuestion="quiz?.questions?.length" 
                            @update:answer="(val: string) => onSubmitAnswer(val, index)"
                             />
                    </template>
                </Stepper>
            </div>
        </div>
    </div>

    <Toast ref="toastService" />
</template>

<script lang="ts">
import type { QuizApi, ScoreApi } from '@/api';
import type { QuizDto } from '@/api/models';
import QuestionComponent from '@/components/QuestionComponent.vue';
import Stepper from '@/components/Stepper.vue';
import i18n from '@/i18n/i18n';
import { inject, ref, watch, type Ref } from 'vue';
import { useRouter } from 'vue-router';
import Toast from '../components/Toast.vue';

export default {
    props: {
        code: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const stepsConfig: Ref<string[]> = ref([]);
        const quiz: Ref<Partial<QuizDto | undefined>> = ref(undefined);
        const code: Ref<String | undefined> = ref(props.code);
        const stepperRef: Ref<typeof Stepper | null> = ref(null);
        const quizApi = inject('QuizApi') as QuizApi;
        const scoreApi = inject('ScoreApi') as ScoreApi;
        const responses: Ref<string[]> = ref([]);
        const toastService: Ref<typeof Toast | null> = ref(null);
        const router = useRouter();

        if (code.value) {
            loadQuiz();
        }

        watch(() => props.code, () => {
            code.value = props.code;
            loadQuiz();
        });

        async function loadQuiz() {
            quizApi.getQuizById({ uidQuiz: code.value as string })
                .then(response => {
                    quiz.value = response;
                    stepsConfig.value = (quiz?.value?.questions) ?
                        quiz?.value?.questions?.map(question => question.title) :
                        [];
                });
        }

        function goBack(){
            stepperRef?.value?.prev();
        }

        function goNext(){
            stepperRef?.value?.next();
        }

        function onSubmitAnswer(val: string, index: number){
            responses.value[index] = val;

            const isAddedAllResponses = responses?.value
                ?.filter(a => a && a !== null && a !== undefined && a !== '')
                ?.length === quiz?.value?.questions?.length;

            if(isAddedAllResponses){
                console.log(responses.value);
            }
            scoreApi.completeQuiz({
                scoreDto: {
                    customerId: '',
                    quizId: quiz?.value?.id!,
                    score: 0,
                }
            })
            .then(() => {
                toastService.value?.show(i18n.global.t('questions_submit_success'));
                router.push({ name: 'home' })
            })
            .catch(() => toastService.value?.showError(i18n.global.t('questions_submit_error')))
            
        }

        return {
            stepsConfig,
            quiz,
            stepperRef,
            goBack,
            goNext,
            onSubmitAnswer
        }
    },
    components: {
        Toast,
        QuestionComponent,
        Stepper
    }
}
</script>

<style></style>