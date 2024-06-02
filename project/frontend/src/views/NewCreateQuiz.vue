<template>
    <div class="container">
        <div class="row">
            <div class="col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 mb-3 mx-auto">
                <div class="stepper-container mx-auto">
                    <v-stepper hide-actions :items="steps" v-model="currentStep" class="text-bg-light">
                        <template v-slot:item.1>
                            <FirstStepNewQuiz @next="onNextFirstStep" @update:counter="onUpdateValuesFirstStep"/>
                        </template>
                        <template v-slot:item.2>
                            <PreviewStepNewQuiz :prev-values="firstStepValues" @prev="currentStep = 1" />
                        </template>
                    </v-stepper>
                </div>
            </div>

            <div class="col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6 mb-3 mx-auto">
               <CreateQuestionsWindows :windows="firstStepValues?.numQuestions"/>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import Integer from '@/components/Integer.vue';
import Stepper from '@/components/Stepper.vue';
import Toast from '@/components/Toast.vue';
import FirstStepNewQuiz from '@/components/create-quiz/FirstStepNewQuiz.vue';
import PreviewStepNewQuiz from '@/components/create-quiz/PreviewStepNewQuiz.vue';
import CreateQuestionsWindows from '@/components/create-quiz/CreateQuestionsWindows.vue';
import i18n from '@/i18n/i18n';
import { v4 as uuidv4 } from 'uuid';
import { ref, type Ref } from 'vue';

export default {
    name: 'NewCreateQuiz',
    setup() {
        const toastService: Ref<typeof Toast | null> = ref(null);
        const steps = ref([
            i18n.global.t('createquiz_title'),
            i18n.global.t('createquiz_title_preview'),
        ]);
        const firstStepValues: Ref<any> = ref();
        const currentStep = ref(1);

        function onNextFirstStep(value: any) {
            firstStepValues.value = value;
            currentStep.value = 2;
        }

        /*
        function copyToClip() {
            navigator.clipboard.writeText(linkToQuiz.value as string)
                .then(() => {
                })
                .catch((error) => {
                    console.error('Errore durante la copia del testo nella clipboard:', error);
                });
        }
        */

        function onUpdateValuesFirstStep(value: any){
            firstStepValues.value = value;
        }

        return {
            toastService,
            steps,
            onNextFirstStep,
            currentStep,
            firstStepValues,
            onUpdateValuesFirstStep
        };
    },
    components: {
        Integer,
        Stepper,
        Toast,
        FirstStepNewQuiz,
        PreviewStepNewQuiz,
        CreateQuestionsWindows
    }
};
</script>

<style scoped>
div.row div {
    min-height: 20rem;
}
div.v-stepper-header{
    background-color: var(--bs-card-cap-bg);
}
</style>