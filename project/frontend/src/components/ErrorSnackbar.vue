<template>
    <v-snackbar v-model="snackbar" color="deep-purple-accent-4" 
        elevation="24" v-html="sanitizer.sanitizeString(text)">
    </v-snackbar>
</template>

<script lang="ts">
import { useErrorHandling } from '@/stores/errorHandling';
import { Sanitizer } from '@/utils/Sanitizer';
import { defineComponent, inject, ref } from 'vue';

export default defineComponent({
    setup(){
        const snackbar = ref(false);
        const text = ref('');
        const errorHandling = useErrorHandling();
        const sanitizer = inject('Sanitizer') as Sanitizer;

        errorHandling.errorsSubject
            .subscribe(value => {
                if(value && value.display){
                    snackbar.value = true;
                    text.value = value.text;
                }
            })

        return {
            snackbar,
            text,
            sanitizer
        }
    }
})

</script>
<style></style>