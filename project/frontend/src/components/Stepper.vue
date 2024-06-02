<template>
    <div class="stepper-container">
        <ul class="nav nav-pills mb-3 d-flex justify-content-center" id="pills-tab" role="tablist">
            <li v-for="(step, index) in stepsConfig" :key="index" class="nav-item d-flex flex-reverse"
                role="presentation">
                <div class="step-content">
                    <button class="btn rounded-circle mx-0 mb-2 p-2 mt-2 disabled"
                        :class="{ 'btn-primary': index === currentStep, 'btn-outline-secondary': index !== currentStep }"
                        :id="'pills-' + index + '-tab'" data-bs-toggle="pill" :data-bs-target="'#pills-' + index"
                        type="button" role="tab" :aria-controls="'pills-' + index"
                        :aria-selected="index === currentStep">
                        {{ index + 1 }}
                    </button>
                        
                    <p v-if="showNames" class="col text-center">{{ step }}</p>
                </div>
            </li>
        </ul>
        <div class="tab-content" id="pills-tabContent">
            <div v-for="(step, index) in stepsConfig" :key="index" class="tab-pane fade"
                :class="{ show: index === currentStep, active: index === currentStep }" :id="'pills-' + index"
                role="tabpanel" :aria-labelledby="'pills-' + index + '-tab'">
                <slot :name="'step-' + index"></slot>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { ref, defineComponent } from 'vue';

export default defineComponent({
    name: 'Stepper',
    props: {
        stepsConfig: {
            type: Array,
            required: true
        },
        showNames: {
            type: Boolean,
            required: false,
            default: false
        }
    },
    setup(props) {
        const currentStep = ref(0);

        function next(){
            if(currentStep.value < props.stepsConfig?.length - 1 ){
                currentStep.value = currentStep.value +1; 
            }
        }

        function prev(){
            if(currentStep.value > 0 ){
                currentStep.value = currentStep.value - 1; 
            }
        }

        return {
            currentStep,
            next,
            prev
        };
    }
});
</script>

<style scoped>
.nav-pills .nav-link.active {
    background-color: #007bff;
}

button.btn {
    cursor: pointer;
}

div.step-content{
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-left: 1rem;
    margin-right: 1rem;
    
    button{
        width: 3rem;
        height: 3rem;
        align-items: center;
        justify-content: center;
        display: flex;
        cursor: unset;
        opacity: unset;
    }
}

.stepper-container{
    background-color: white;
    padding: 1rem;
    border-radius: 8px;
}
</style>