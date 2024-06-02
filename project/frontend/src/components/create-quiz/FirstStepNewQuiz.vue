<template>
    <div class="row">
        <h2 class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-4 mx-auto text-center">{{ $t('createquiz_title') }}</h2>
        <form @submit.prevent="onNext">
            <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-12 mb-3 mx-auto">
                <v-text-field :label="$t('createquiz_titlequiz')" variant="outlined" 
                    ref="values.title" v-model="values.title" required
                    hide-details="auto"
                    :rules="[minLength]"
                ></v-text-field>
            </div>
            <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-3 mx-auto">
                <v-text-field :label="$t('createquiz_description')" variant="outlined" 
                    ref="values.description" v-model="values.description" required
                    hide-details="auto"
                    :rules="[minLength]"
                ></v-text-field>
            </div>
            <div class="row mb-3">
                <div class="col-6 col-sm-6 col-lg-3 col-md-6 col-xl-6 ms-auto my-auto">
                    <p class="my-auto">{{ $t('createquiz_numQuestions') }}</p>
                </div>
                <div class="col-6 col-sm-6 col-lg-3 col-md-6 col-xl-6 me-auto d-flex justify-content-end">
                    <Integer 
                        :counter="values.numQuestions" 
                        @update:counter="onUpdateCounter" />
                </div>
            </div>
            <div class="row mb-3"
                v-if="confirmClicked && !Validators.numRange((values?.numQuestions || 0)?.toString(), { max: 30, min: 1 })">
                <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-3 mx-auto">
                    <p class="text-danger">{{ $t('validators_numRange', { min: 1, max: 30 }) }}</p>
                </div>
            </div>

            <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-3 pt-3 mx-auto d-flex justify-content-between">
                <button type="button" class="btn btn-outline-secondary" disabled>{{$t('question_component_back')}}</button>
                <button type="submit" class="btn btn-primary">{{$t('question_component_next')}}</button>
            </div>
        </form>
    </div>
</template>

<script lang="ts">
import { Validators } from '@/utils/Validators';
import { defineComponent, ref, type Ref } from 'vue';
import Integer from '../Integer.vue';
import i18n from '@/i18n/i18n';

export default defineComponent({
    name: 'FirstStepNewQuiz',
    emits: ['next', 'update:counter'],
    setup(_, { emit }) {
        const values: Ref<{ numQuestions?: number, title?: string, description?: string }> = ref({
            numQuestions: 1
        });
        emit('update:counter', values.value);
        const confirmClicked = ref(false);

        function onNext(){
            confirmClicked.value = true;
            if(hasNoErrors()){
                emit('next', values.value);
            }
        }

        function hasNoErrors(): boolean {
            return !!values.value?.title && !!values.value?.description &&
                Validators.minLength(values.value.title) &&
                Validators.minLength(values.value.description) &&
                Validators.numRange((values.value?.numQuestions || 0).toString(), { min: 1, max: 30 })
        }

        function minLength(value: any){
            return Validators.minLength(value) || i18n.global.t('validators_minlength');
        }

        function onUpdateCounter(val: number){
            values.value.numQuestions = val;
            emit('update:counter', values.value);
        }

        return {
            values,
            Validators,
            confirmClicked,
            minLength,
            onNext,
            onUpdateCounter
        }
    },
    components: {
        Integer
    }
})
</script>

<style></style>