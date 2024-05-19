<template>
    <div ref="toast" class="toast bg-success toast-container align-items-center text-bg-primary border-0" role="alert"
        aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                {{ message }}
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import type { Ref } from 'vue';
import { defineComponent } from 'vue';
import { ref } from 'vue';

export default defineComponent({
    name: 'Toast',
    setup() {
        const toast: Ref<any> = ref(null);
        const message = ref('');

        function show(val: any) {
            message.value = val;
            toast.value?.classList?.add('show');
            setTimeout(() => {
                toast.value?.classList?.remove('show');
            }, 3000);
        }
        return {
            show,
            toast,
            message
        }
    }
});
</script>

<style scoped>
.toast-container {
    position: fixed;
    top: 4rem;
    right: 1rem;
    width: 30%;
    z-index: 9999;
}

@media (max-width: 1024px) {
    .toast-container {
        position: fixed;
        top: 4rem;
        right: 1rem;
        width: 80%;
        z-index: 9999;
        text-align: center;
    }
}
</style>