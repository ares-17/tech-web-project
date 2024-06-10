<template>
    <v-snackbar v-model="snackbar" color="deep-purple-accent-4" elevation="24">
        {{ text }}
    </v-snackbar>
</template>
<script lang="ts">
import { useErrorHandling } from '@/stores/errorHandling';
import { defineComponent, ref } from 'vue';


export default defineComponent({
    setup(){
        const snackbar = ref(false);
        const text = ref('');
        const errorHandling = useErrorHandling();
        errorHandling.errorsSubject
            .subscribe(value => {
                if(value && value.display){
                    snackbar.value = true;
                    text.value = value.text;
                }
            })

        return {
            snackbar,
            text
        }
    }
})

</script>
<style></style>