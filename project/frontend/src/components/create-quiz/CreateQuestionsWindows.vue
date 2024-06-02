<template>
    <div v-if="windowsRef && windowsRef !== 0" class="h-100">
        <div class="card h-100 w-100 text-bg-light ">
            <div class="card-header header-domande">Sezione domande del quiz</div>
            <div class="card-body d-flex justify-center align-center h-100 w-100">
                <v-window v-model="currentWindow" show-arrows class="h-100 w-100 ">
                    <v-window-item v-for="n in windowsRef" :key="n">
                        <v-card class="d-flex flex-column h-100 text-bg-light">
                            <h2 class="text-center mb-4 mt-1">Domanda {{ n }} di {{ windowsRef }}</h2>
                            <CreateQuestionComponent />
                        </v-card>
                    </v-window-item>
                </v-window>
            </div>
        </div>
    </div>
    <div v-else class="card h-100 text-bg-light">
        <div class="card-header">Sezione domande del quiz</div>
        <div class="card-body d-flex justify-center align-center h-100">
            <span>Seleziona il numero di domande per poterle modificare</span>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue';
import CreateQuestionComponent from './CreateQuestionComponent.vue';

export default defineComponent({
    name: 'CreateQuestionsWindows',
    props: {
        windows: {
            type: Number,
            required: false,
            default: 0
        }
    },
    setup(props, { emit }) {
        const currentWindow = ref(1);
        const windowsRef = ref(props.windows);
        watch(() => props.windows, () => windowsRef.value = props.windows);

        return {
            windowsRef,
            currentWindow
        }
    },
    components: {
        CreateQuestionComponent,
    }
})

</script>
<style>
div.v-window {
    height: 100%;
}

div.v-window__container {
    height: 100%;
}

div.v-window-item {
    height: 100%;
}

div.header-domande{
    height: 5rem;
    display: flex;
    align-items: center;
    box-shadow: 0px 3px 1px -2px var(--v-shadow-key-umbra-opacity, rgba(0, 0, 0, 0.2)), 0px 2px 2px 0px var(--v-shadow-key-penumbra-opacity, rgba(0, 0, 0, 0.14)), 0px 1px 5px 0px var(--v-shadow-key-ambient-opacity, rgba(0, 0, 0, 0.12));
}
</style>