<template>
    <div class="row">
        <form @submit.prevent="submitQuestion">
            <div class="col-12 mx-auto mb-3 text-center" 
                v-html="Utils.toMarkdown(question?.title)"></div>
            <component v-if="question" :is="currentComponent" :question="question" 
                @update:answer="(value: string) => emit('update:answer', value)" />
        </form>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue';
import MultipleChoiceQuestionComponent from '../components/MultipleChoiceQuestionComponent.vue';
import OpenQuestionComponent from '../components/OpenQuestionComponent.vue';
import MultipleChoiceQuestion from '../models/MultipleChoiceQuestion.model';
import Question from '../models/Question.model';
import OpenChoiceQuestion from '../models/OpenChoiceQuestion.model';
import type { Ref } from 'vue';
import Utils from '../utils/Utils';

export default defineComponent({
    props: {
        question: {
            type: Question,
            required: true,
        }
    },
    emits: ['update:answer'],
    setup(props, { emit }) {
        const currentComponent: Ref<any> = ref(null);

        watch(
            () => props.question,
            (newQuestion) => {
                currentComponent.value = checkQuestionType(newQuestion);
            },
            { immediate: true }
        );

        function submitQuestion() {

        }

        function checkQuestionType(question: Question | null) {
            if (!question || !question.constructor) {
                return null;
            }

            /*
            if(question instanceof MultipleChoiceQuestion){
                return MultipleChoiceQuestionComponent;
            } else if(question instanceof OpenChoiceQuestion){
                return OpenQuestionComponent;
            }
            throw new Error('Error on question type in QuestionComponent');
            */

            return OpenQuestionComponent;
        }

        function onAnswerSubmit(value: string){
            emit('update:answer', value)
        }

        return {
            submitQuestion,
            currentComponent,
            Utils,
            onAnswerSubmit
        };
    },
    components: {

    }
});
</script>

<style scoped></style>