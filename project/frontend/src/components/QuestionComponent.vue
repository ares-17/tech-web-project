<template>
    <div class="row">
        <form @submit.prevent="submitQuestion">
            <div class="col-12 mx-auto mb-3 text-center" v-html="Utils.toMarkdown(question?.title)"></div>
            <component v-if="question" :is="currentComponent" :question="question"
                @update:answer="(value: string) => saveResponse(value)" />
        </form>
        <div class="row mt-3">
            <div class="col-12 col-md-6 col-sm-6 col-lg-6 col-xl-6 mb-3 mx-auto mx-auto">
                <button :class="{ disabled: index === 0 }" type="submit" @click="() => $emit('click:back')"
                    class="btn btn-outline-secondary w-100">
                    <i class="bi bi-arrow-left"></i>
                    {{ $t('question_component_back') }}
                </button>
            </div>
            <div class="col-12 col-md-6 col-sm-6 col-lg-6 col-xl-6 mb-3 mx-auto mx-auto">
                <button :class="{ disabled: !response }" 
                    @click="() => $emit('click:next')" type="submit"
                    class="btn btn-primary w-100">
                    {{ index === (totalQuestion - 1) ?
                        $t('question_component_last_next') :
                        $t('question_component_next')
                    }}
                    <i class="bi bi-arrow-right"></i>
                </button>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import type { QuestionDto } from '@/api/models';
import type { PropType, Ref } from 'vue';
import { defineComponent, ref, watch } from 'vue';
import OpenQuestionComponent from '../components/OpenQuestionComponent.vue';
import Utils from '../utils/Utils';
import MultipleChoiceQuestionComponent from './MultipleChoiceQuestionComponent.vue';

export default defineComponent({
    props: {
        question: {
            type: Object as PropType<QuestionDto>,
            required: true,
        },
        index: {
            type: Number,
            required: true
        },
        totalQuestion: {
            type: Number,
            required: true
        }
    },
    emits: ['update:answer', 'click:back', 'click:next'],
    setup(props, { emit }) {
        const currentComponent: Ref<any> = ref(null);
        const response: Ref<string | undefined> = ref();

        watch(
            () => props.question,
            (newQuestion) => {
                currentComponent.value = checkQuestionType(newQuestion as QuestionDto);
            },
            { immediate: true }
        );

        function submitQuestion() {

        }

        function checkQuestionType(question: QuestionDto | null) {
            if (!question || !question.answers) {
                return null;
            }

            if (question.answers?.length > 1) {
                return MultipleChoiceQuestionComponent;
            } else if (question.answers?.length === 1) {
                return OpenQuestionComponent;
            }
            throw new Error('Error on question type in QuestionComponent');
        }

        function onAnswerSubmit(value: string) {
            emit('update:answer', value)
        }

        function saveResponse(value: string){
            response.value = value; 
            emit('update:answer', value);
        }

        return {
            submitQuestion,
            currentComponent,
            Utils,
            onAnswerSubmit,
            saveResponse,
            response
        };
    },
    components: {

    }
});
</script>

<style scoped></style>