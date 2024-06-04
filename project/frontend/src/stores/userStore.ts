import { defineStore } from 'pinia';
import { reactive, watch } from 'vue';
import type { AuthenticationDto } from '@/api/models';

export const useUserStore = defineStore('userStore', () => {

    const state = reactive<{ authDto?: AuthenticationDto, username?: string }>({
        authDto: undefined,
        username: undefined
    });
    const eventListeners: ((newVal: AuthenticationDto | undefined, username: string | undefined) => void)[] = [];

    function clean() {
        state.authDto = undefined;
        state.username = undefined;
        eventListeners.forEach(listener => listener(undefined, undefined));
    }

    function isAuthenticated() {
        return state.authDto && 
               state.authDto.expiresIn &&
               state.authDto.token;
    }

    function setUser(data: AuthenticationDto, username: string) {
        state.authDto = data;
        state.username = username;
        eventListeners.forEach(listener => listener(state.authDto, state.username));
    }

    function addEventListener(listener: (newVal: AuthenticationDto | undefined, username: string | undefined) => void) {
        eventListeners.push(listener);
    }

    function removeEventListener(listener: (newVal: AuthenticationDto | undefined) => void) {
        const index = eventListeners.indexOf(listener);
        if (index !== -1) {
            eventListeners.splice(index, 1);
        }
    }

    return {
        state,
        clean,
        isAuthenticated,
        setUser,
        addEventListener,
        removeEventListener,
    };
});
