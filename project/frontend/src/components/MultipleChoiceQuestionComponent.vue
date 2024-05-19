<template>
    <div v-for="(option, index) in question?.choices" :key="index" class="form-check ms-2 my-2">
      <input
        class="form-check-input"
        type="radio"
        :name="option"
        :id="`${option}-${index}`"
        :value="option"
        v-model="selected"
      >
      <label class="form-check-label" :for="`${option}-${index}`" v-html="Utils.toMarkdown(option)">
      </label>
    </div>
</template>

<script lang="ts">
import MultipleChoiceQuestion from '../models/MultipleChoiceQuestion.model';
import { defineComponent, ref, type Ref } from 'vue';
import Utils from '../utils/Utils';
import { watch } from 'vue';

export default defineComponent({
    props: {
        question: {
            type: MultipleChoiceQuestion,
            required: true,
        }
    },
    emits: ['update:selected'],
    setup(props, { emit }) {
        const question: Ref<Partial<MultipleChoiceQuestion>> = ref(props.question);
        const selected: Ref<undefined | string> = ref(undefined);

        watch(selected, () => {
          if(selected){
            emit('update:selected', selected)
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