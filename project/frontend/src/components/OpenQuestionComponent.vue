<template>
    <div class="row">
        <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-12 mb-3 mx-auto">
            <label for="description" class="form-label">{{ $t('openquestion_answer') }}</label>
            <textarea class="form-control" v-model="answer"></textarea>
        </div>
    </div>
</template>

<script lang="ts">
import OpenQuestion from '@/models/OpenChoiceQuestion.model';
import { defineComponent, ref, type Ref } from 'vue';

export default defineComponent({
    props: {
        question: {
            type: OpenQuestion,
            required: true,
        }
    },
    setup(props) {
        const question: Ref<Partial<OpenQuestion>> = ref(props.question);
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