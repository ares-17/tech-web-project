<template>
    <div class="container">
        <div class="row">
            <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mb-3 mx-auto">
                <v-card class="text-bg-light p-5 w-100" elevation="16" v-if="!!quiz">
                    <div class="input-container bg-transparent w-100">
                        <div class="pb-5">
                            <div class="row">
                                <div class="col-12 col-md-6 col-sm-12 col-lg-12 col-xl-12 mb-2">
                                    <v-btn rounded="md" color="primary">
                                        <v-icon icon="mdi-arrow-left" class="mx-3"></v-icon>
                                        {{ $t('go_back_btn') }}
                                    </v-btn>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex justify-content-between align-items-center mb-1">
                            <h1 v-html="(quiz?.title) ? Utils.toMarkdown(quiz?.title) : ''"></h1>
                        </div>
                        <span v-html="(quiz?.description) ? Utils.toMarkdown(quiz?.description) : ''"></span>
                        <v-divider></v-divider>
                        <v-form readonly>
                            <div class="row">
                                <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-6 mb-2 h-100">
                                    <v-text-field v-model="quiz.maxErrors"
                                        :label="$t('quiz_istance_errors')"></v-text-field>
                                </div>
                                <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-6 mb-2 h-100">
                                    <v-text-field v-model="quiz.questions.length"
                                        :label="$t('quiz_istance_num_questions')"></v-text-field>
                                </div>
                            </div>
                        </v-form>
                    </div>
                </v-card>
            </div>

            <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mb-3 mx-auto" v-if="!!quiz">
                <CreateQuestionsWindows :quiz="quiz" :windows="quiz?.questions?.length"
                    @complete="onCompleteQuestions" />
            </div>

        </div>
    </div>
</template>

<script lang="ts">
import { ScoreApi, type QuizApi } from '@/api';
import type { QuestionDto, QuizDto } from '@/api/models';
import type { QuestionResponseDto } from '@/api/models/QuestionResponseDto';
import type { QuizResponseDto } from '@/api/models/QuizResponseDto';
import CreateQuestionsWindows from '@/components/create-quiz/CreateQuestionsWindows.vue';
import { useSessionStore } from '@/stores/sessionStore';
import Utils from '@/utils/Utils';
import { inject, onMounted, ref, type Ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
    name: 'CreateQuiz',
    props: {
        id: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const quizApi = inject('QuizApi') as QuizApi;
        const router = useRouter();
        const scoreApi = inject('ScoreApi') as ScoreApi;
        const quiz: Ref<QuizDto | undefined> = ref();
        const sessionStorage = useSessionStore();

        onMounted(() => {
            quizApi.getQuizById({ uidQuiz: props.id })
                .then(res => quiz.value = res)
                .catch(e => console.log(e));
        })

        function questionToQuestionResponseDto(question: QuestionDto): QuestionResponseDto {
            if (!question) {
                throw new Error('Question in questionToQuestionResponseDto is null');
            }
            if (!question.answers || question.answers?.length === 0) {
                throw new Error('Answers array in questionToQuestionResponseDto is null');
            }

            const answer = (question.answers?.length === 1) ?
                question.answers[0].text :
                question.answers?.find(a => !!a.isCorrect)?.text;

            return {
                answer,
                id: question.id
            }
        }

        function onCompleteQuestions(questions: QuestionDto[]) {
            const idCustomer = (sessionStorage.getFromSessionStorage('idCustomer')) ?
                sessionStorage.getFromSessionStorage('idCustomer') as string :
                undefined;

            const quizResponseDto: QuizResponseDto = {
                id: quiz.value?.id,
                questions: questions.map(questionToQuestionResponseDto),
                isCustomerAnonymous: !idCustomer,
                idCustomer
            };
            scoreApi.completeQuiz({ quizResponseDto })
                .then(() => router.push('/'))
                .catch((e) => console.log(e));
        }

        return {
            quiz,
            Utils,
            onCompleteQuestions
        };
    },
    components: {
        CreateQuestionsWindows
    }
};
</script>

<style scoped></style>