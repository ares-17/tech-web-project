<template>
    <div class="counter">
        <button type="button" class="btn btn-light border" @click="decrement">-</button>
        <span>{{ count }}</span>
        <button type="button" class="btn btn-light border" @click="increment">+</button>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue';

export default defineComponent({
    props: {
        counter: {
            type: Number,
            required: false,
            default: 0
        },
        maybeNegative: {
            type: Boolean,
            required: false,
            default: false
        }
    },
    emits: ['update:counter'],
    setup(props, { emit }) {
        const count = ref(props.counter);

        const increment = () => {
            count.value++;
            emit('update:counter', count.value);
        };

        const decrement = () => {
            if (!props.maybeNegative && count.value < 1) {
                return;
            }
            count.value--;
            emit('update:counter', count.value);
        };

        return {
            count,
            increment,
            decrement
        };
    }
});
</script>

<style scoped>
.counter {
    display: flex;
    align-items: center;

    button{
        padding-left: 1rem;
        padding-right: 1rem;
    }
    span{
        margin: 0.5rem;
    }
}

.counter button {
    margin: 0 10px;
    padding: 10px;
    font-size: 16px;
}

.counter span {
    font-size: 18px;
    font-weight: bold;
}
</style>