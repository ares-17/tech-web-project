<template>
  <div class="container mt-5 pt-5">
    <Stepper :stepsConfig="stepsConfig" ref="stepperRef" showNames>
    <template #step-0>
      <div class="row">
        <h2 class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto text-center">{{ $t('createquiz_title') }}</h2>
        <form @submit.prevent="viewPreview">
          <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto">
            <label for="title" class="form-label">{{ $t('createquiz_titlequiz') }}</label>
            <input type="text" class="form-control" v-model="quiz.title">
            <div v-if="confirmClicked && !Validators.minLength(quiz?.title || '')">
              <p class="text-danger">{{ $t('validators_minlength') }}</p>
            </div>
          </div>
          <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto">
            <label for="description" class="form-label">{{ $t('createquiz_description') }}</label>
            <textarea class="form-control" v-model="quiz.description"></textarea>
            <div v-if="confirmClicked && !Validators.minLength(quiz?.description || '')">
              <p class="text-danger">{{ $t('validators_minlength') }}</p>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-6 col-sm-6 col-lg-3 col-md-6 col-xl-3 ms-auto my-auto">
              <p class="my-auto">{{ $t('createquiz_numQuestions') }}</p>
            </div>
            <div class="col-6 col-sm-6 col-lg-3 col-md-6 col-xl-3 me-auto d-flex justify-content-end">
              <Integer :counter="quiz.numQuestions" @update:counter="val => quiz.numQuestions = val" />
            </div>
          </div>
          <div class="row mb-3"
            v-if="confirmClicked && !Validators.numRange(quiz?.numQuestions || 0, { max: 30, min: 1 })">
            <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto">
              <p class="text-danger">{{ $t('validators_numRange', { min: 1, max: 30 }) }}</p>
            </div>
          </div>

          <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto">
            <button type="submit" class="btn btn-primary w-100" @click="confirmClicked = true">{{
    $t('createquiz_view_preview')
  }}</button>
          </div>
        </form>
      </div>
    </template>
    <template #step-1>
      <!-- Preview -->
      <div>
        <h2 class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto text-center">{{ $t('createquiz_title_preview') }}</h2>
        <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto">
          <label for="markdownpreviewtitle" class="form-label">{{ $t('createquiz_titlequiz') }}</label>
          <div id="markdownpreviewtitle" class="border rounded p-1" v-html="Utils.toMarkdown(quiz.title)"></div>
        </div>
        <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto">
          <label for="markdownpreviewdescr" class="form-label">{{ $t('createquiz_description') }}</label>
          <div id="markdownpreviewdescr" class="border rounded p-1" v-html="Utils.toMarkdown(quiz.description)" ></div>
        </div>
        <div class="row">
          <div class="col-2 col-md-2 col-sm-2 col-lg-1 col-xl-1 mb-3 ms-auto">
          <button type="submit" class="btn btn-outline-secondary w-100" @click="backToStep1">
            <i class="bi bi-arrow-left"></i>
          </button>
        </div>
        <div class="col-10 col-md-10 col-sm-10 col-lg-5 col-xl-5 mb-3 me-auto">
          <button type="submit" class="btn btn-primary w-100" @click="createQuiz">{{
    $t('createquiz_submitquiz')
            }}</button>
        </div>
        </div>
        <div v-if="linkToQuiz && uuidQuiz" class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto border border-success rounded my-3 p-2 d-flex justify-content-center">
          <a class="text-center my-auto text-success" target="_blank" :href="linkToQuiz" >{{ uuidQuiz }}</a>
          <button type="button" class="btn my-auto"><i class="bi bi-copy" @click="copyToClip"></i></button>
        </div>
      </div>
    </template>
  </Stepper>
  </div>

  <Toast ref="toastService"/>

</template>

<script lang="ts">
import Integer from '@/components/Integer.vue';
import Stepper from '@/components/Stepper.vue';
import Toast from '@/components/Toast.vue';
import i18n from '@/i18n/i18n';
import { Validators } from '@/utils/Validators';
import { v4 as uuidv4 } from 'uuid';
import { ref, type Ref } from 'vue';
import Utils from '../utils/Utils';
import type { QuizDto } from '@/api/models';

export default {
  setup() {
    const quiz: Ref<Partial<QuizDto>> = ref({});
    const confirmClicked = ref(false);
    const formHasErrors = ref(true);
    const stepperRef: Ref<typeof Stepper | null> = ref(null);
    const uuidQuiz: Ref<undefined | string> = ref(undefined);
    const linkToQuiz: Ref<undefined | string> = ref(undefined);
    const toastService: Ref<typeof Toast | null> = ref(null);
    
    function viewPreview() {
      formHasErrors.value = checkErrors();
      if (!formHasErrors.value) {
        stepperRef?.value?.next();
      }
    };

    async function createQuiz(){
      if (!formHasErrors.value) {
        uuidQuiz.value = await Promise.resolve(uuidv4());
        linkToQuiz.value = `http://localhost:5173/quiz/${uuidQuiz.value}`;
        toastService.value?.show(i18n.global.t('createquiz_success_toast'));
      }
    }

    function backToStep1(){
      uuidQuiz.value = undefined;
      linkToQuiz.value = undefined;
      confirmClicked.value = false;
      stepperRef?.value?.prev();
    }

    function checkErrors() {
      return !Validators.minLength(quiz.value?.title || '') ||
        !Validators.minLength(quiz.value?.description || '') ||
        !Validators.numRange(quiz.value?.numQuestions || 0, { max: 30, min: 1 });
    }

    const stepsConfig = [i18n.global.t('create_quiz_step1'), i18n.global.t('create_quiz_step2')];

    function copyToClip() {
      navigator.clipboard.writeText(linkToQuiz.value as string)
        .then(() => {
        })
        .catch((error) => {
          console.error('Errore durante la copia del testo nella clipboard:', error);
        });
    }

    return {
      quiz,
      viewPreview,
      confirmClicked,
      Validators,
      formHasErrors,
      stepsConfig,
      stepperRef,
      backToStep1,
      createQuiz,
      linkToQuiz,
      copyToClip,
      uuidQuiz,
      toastService,
      Utils
    };
  },
  components: {
    Integer,
    Stepper,
    Toast
  }
};
</script>

<style scoped></style>