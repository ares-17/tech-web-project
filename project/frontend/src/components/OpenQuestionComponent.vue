<template>
    <div class="row">
        <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-12 mb-3 mx-auto">
            <label for="description" class="form-label">{{ $t('openquestion_answer') }}</label>
            <textarea class="form-control" v-model="answer" @keyup="$emit('update:answer', answer)"></textarea>
        </div>
    </div>
</template>

<script lang="ts">
import type { QuestionDto } from '@/api/models';
import type { PropType } from 'vue';
import { defineComponent, ref, type Ref } from 'vue';

export default defineComponent({
    props: {
        question: {
            type: Object as PropType<QuestionDto>,
            required: true,
        }
    },
    emits: ['update:answer'],
    setup(props) {
        const question: Ref<Partial<QuestionDto>> = ref(props.question);
        const answer: Ref<undefined | string> = ref(undefined)

        return {
            question,
            answer
        };
    },
    components: {

    }
});
</script>

<style scoped></style>