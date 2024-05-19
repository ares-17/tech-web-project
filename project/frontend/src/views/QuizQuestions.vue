<template>
    <div class="container mt-5 pt-5">
        <div class="row mx-auto">
            <div class="col-12 col-xl-6 col-lg-6 mx-auto">
                <Stepper v-if="quiz && quiz.questions?.length" :stepsConfig="stepsConfig" ref="stepperRef">
                    <template v-for="(question, index) of quiz?.questions" v-slot:[`step-${index}`]>
                        <QuestionComponent :question="question" 
                            @update:answer="(val: string) => onSubmitAnswer(val, index)" />
                        <div class="row mt-3">
                            <div class="col-12 col-md-6 col-sm-6 col-lg-6 col-xl-6 mb-3 mx-auto mx-auto">
                                <button :class="{ disabled: index === 0 }" type="submit"
                                    @click="goBack()"
                                    class="btn btn-outline-secondary w-100">
                                    <i class="bi bi-arrow-left"></i>
                                    {{ $t('question_component_back') }}
                                </button>
                            </div>
                            <div class="col-12 col-md-6 col-sm-6 col-lg-6 col-xl-6 mb-3 mx-auto mx-auto">
                                <button :class="{ disabled: index === (quiz.questions?.length - 1) }" 
                                    @click="goNext()"
                                    type="submit"
                                    class="btn btn-primary w-100">
                                    {{ index === (quiz.questions?.length - 1) ? 
                                        $t('question_component_last_next') : 
                                        $t('question_component_next') 
                                    }}
                                    <i class="bi bi-arrow-right"></i>
                                </button>
                            </div>
                        </div>
                    </template>
                </Stepper>
            </div>
        </div>
    </div>

    <Toast ref="toastService" />
</template>

<script lang="ts">
import Toast from '../components/Toast.vue';
import Quiz from '@/models/Quiz.model';
import { watch, ref, type Ref } from 'vue';
import QuestionComponent from '@/components/QuestionComponent.vue';
import type MultipleChoiceQuestion from '@/models/MultipleChoiceQuestion.model';
import Stepper from '@/components/Stepper.vue';
import type OpenQuestion from '@/models/OpenChoiceQuestion.model';

export default {
    props: {
        code: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const stepsConfig: Ref<string[]> = ref([]);
        const quiz: Ref<Partial<Quiz | undefined>> = ref(undefined);
        const code: Ref<String | undefined> = ref(props.code);
        const stepperRef: Ref<typeof Stepper | null> = ref(null);
        const answers: Ref<undefined | any> = ref();

        if (code.value) {
            loadQuiz();
        }

        watch(() => props.code, () => {
            code.value = props.code;
            loadQuiz();
        });

        async function loadQuiz() {
            const questions: OpenQuestion[] = [{
                answer: 'Si',
                isMultipleChoices: false,
                title: '## Titolo particolare'
            },
            {
                answer: 'Si',
                isMultipleChoices: false,
                title: '## Titolo particolare 2'
            }];

            quiz.value = await Promise.resolve({
                createdAt: '12/04/2025',
                createdBy: 'aress',
                description: 'description',
                questions: questions,
                title: '# Un quiz fantastico!',
                uid: '12343423'
            });
            stepsConfig.value = (quiz?.value?.questions) ?
                quiz?.value?.questions?.map(question => question.title) :
                [];
        }

        function goBack(){
            stepperRef?.value?.prev();
        }

        function goNext(){
            stepperRef?.value?.next();
        }

        function onSubmitAnswer(val: string, index: number){
            
        }

        return {
            stepsConfig,
            quiz,
            stepperRef,
            goBack,
            goNext
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