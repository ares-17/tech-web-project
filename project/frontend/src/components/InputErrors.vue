<template>
    <div v-for="validator of validators">
        <div v-if="validator(value)">
        
        </div>
    </div>
    <div v-if="confirmClicked && !Validators.minLength(quiz?.title || '')">
        <p class="text-danger">{{ $t('validators_minlength') }}</p>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
    props: {
        value: {
            required: true,
        },
        validators: {
            required: true,
            type: Array<Function>
        },
        i18nValues: {
            required: false,
            type: Array,
            default: []
        }
    },
    setup() {
    },
})

</script>

<style></style>