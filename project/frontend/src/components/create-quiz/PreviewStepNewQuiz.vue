<template>
  <div>
    <h2 class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-3 mx-auto text-center">{{
      $t('createquiz_title_preview') }}</h2>
    <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-3 mx-auto">
      <label for="markdownpreviewtitle" class="form-label">{{ $t('createquiz_titlequiz') }}</label>
      <div id="markdownpreviewtitle" class="border rounded p-1" v-html="Utils.toMarkdown(values.title!)"></div>
    </div>
    <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-3 mx-auto">
      <label for="markdownpreviewdescr" class="form-label">{{ $t('createquiz_description') }}</label>
      <div id="markdownpreviewdescr" class="border rounded p-1" v-html="Utils.toMarkdown(values.description!)"></div>
    </div>
    <!--
        <div v-if="linkToQuiz && uuidQuiz" class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-3 mx-auto border border-success rounded my-3 p-2 d-flex justify-content-center">
          <a class="text-center my-auto text-success" target="_blank" :href="linkToQuiz" >{{ uuidQuiz }}</a>
          <button type="button" class="btn my-auto"><i class="bi bi-copy" @click="copyToClip"></i></button>
        </div>
        -->
  </div>
  <v-divider></v-divider>
  <v-card-actions>
    <v-btn variant="text" @click="$emit('prev')">
      {{ $t('question_component_back') }}
    </v-btn>
    <v-spacer></v-spacer>
  </v-card-actions>
</template>

<script lang="ts">
import Utils from '@/utils/Utils';
import { defineComponent, ref, type PropType, type Ref } from 'vue';
import Integer from '../Integer.vue';

export default defineComponent({
  name: 'FirstStepNewQuiz',
  props: {
    prevValues: {
      required: true,
      type: Object as PropType<{ numQuestions?: number, title?: string, description?: string }>
    }
  },
  emits: ['next', 'prev'],
  setup(props) {
    const values: Ref<{ numQuestions?: number, title?: string, description?: string }> = ref(props.prevValues);

    function createQuiz() {

    }

    return {
      values,
      createQuiz,
      Utils,
    }
  },
  components: {
    Integer
  }
})
</script>

<style></style>