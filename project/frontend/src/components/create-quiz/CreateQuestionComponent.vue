<template>
    <div class="row w-100">
        <div class="col-12">
            <v-radio-group v-model="typeOfQuest" :mandatory="true" inline @update:model-value="resetAnswers">
                <v-radio label="Risposta singola" value="open"></v-radio>
                <v-radio label="Risposte multiple" value="closed"></v-radio>
            </v-radio-group>
        </div>
        <v-divider :thickness="2"></v-divider>
        <div class="col-12">
            <v-text-field :label="$t('create_question_title')" variant="outlined" class="mb-3 ms-1"
                v-model="question.title" required hide-details="auto" :rules="[minLength]"></v-text-field>
            <div v-if="typeOfQuest === 'open'">
                <v-textarea clearable label="Label" variant="outlined" class="ms-1"
                    @update:model-value="updateSingleAnswer"></v-textarea>
            </div>
            <div v-else class="w-100">
                <v-text-field :label="$t('create_question_answer1')" variant="outlined"  class="mb-3 ms-1"
                    @update:model-value="val => updateAnswer(0, val)" required hide-details="auto" 
                    :rules="[minLength]">
                    <template v-slot:prepend>
                        <i class="bi bi-1-circle-fill"></i>
                    </template>
                </v-text-field>
                <v-text-field :label="$t('create_question_answer2')" variant="outlined"  class="mb-3 ms-1"
                    @update:model-value="val => updateAnswer(1, val)" required hide-details="auto" 
                    :rules="[minLength]">
                    <template v-slot:prepend>
                        <i class="bi bi-2-circle-fill"></i>
                    </template>
                </v-text-field>
                <v-text-field :label="$t('create_question_answer3')" variant="outlined"  class="mb-3 ms-1"
                    @update:model-value="val => updateAnswer(2, val)" required hide-details="auto" 
                    :rules="[minLength]">
                    <template v-slot:prepend>
                        <i class="bi bi-3-circle-fill"></i>
                    </template>
                </v-text-field>
                <v-text-field :label="$t('create_question_answer4')" variant="outlined"  class="mb-3 ms-1"
                    @update:model-value="val => updateAnswer(3, val)" required hide-details="auto" 
                    :rules="[minLength]">
                    <template v-slot:prepend>
                        <i class="bi bi-4-circle-fill"></i>
                    </template>
                </v-text-field>
            </div>
        </div>
    </div>
</template>
<script lang="ts">
import type { QuestionDto } from '@/api/models';
import i18n from '@/i18n/i18n';
import { Validators } from '@/utils/Validators';
import { defineComponent, ref, watch, type Ref } from 'vue';
import { VRadio, VRadioGroup } from 'vuetify/components';

export default defineComponent({
    props: {

    },
    setup(props, ctx) {
        const typeOfQuest: Ref<'open' | 'closed'> = ref('open');
        const question: Ref<Partial<QuestionDto>> = ref({});

        function minLength(value: any) {
            return Validators.minLength(value) || i18n.global.t('validators_minlength');
        }

        function resetAnswers() {
            question.value.answers = undefined;
        }

        function updateSingleAnswer(value: string) {
            question.value.answers = [{
                isCorrect: true,
                text: value
            }]
        }

        function updateAnswer(index: number, value: string){
            if(!question.value?.answers){
                question.value.answers = [];
            }
            question.value.answers[index] = {
                isCorrect: false,
                text: value
            }
        }

        return {
            typeOfQuest,
            minLength,
            question,
            resetAnswers,
            updateSingleAnswer,
            updateAnswer
        }
    },
    components: {
        VRadio,
        VRadioGroup
    }
})

</script>

<style></style>