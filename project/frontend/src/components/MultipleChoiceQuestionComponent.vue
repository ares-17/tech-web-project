<template>
    <div v-for="(option, index) in question?.answers" :key="index" class="form-check ms-2 my-2">
      <input
        class="form-check-input"
        type="radio"
        :name="option.text"
        :id="`${option}-${index}`"
        :value="option?.text"
        v-model="selected"
      >
      <label class="form-check-label" :for="`${option}-${index}`" v-html="Utils.toMarkdown(option.text)">
      </label>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, type Ref } from 'vue';
import Utils from '../utils/Utils';
import { watch } from 'vue';
import type { QuestionDto } from '@/api/models';
import type { PropType } from 'vue';

export default defineComponent({
    props: {
        question: {
            type: Object as PropType<QuestionDto>,
            required: true,
        }
    },
    emits: ['update:answer'],
    setup(props, { emit }) {
        const question: Ref<Partial<QuestionDto>> = ref(props.question);
        const selected: Ref<undefined | string> = ref(undefined);

        watch(selected, () => {
          if(selected){
            emit('update:answer', selected.value)
          }
        })

        return {
            question,
            selected,
            Utils
        };
    },
    components: {
    }
});
</script>

<style scoped></style>