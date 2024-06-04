<template>
    <v-dialog activator="parent" v-model="showDialogState" transition="dialog-bottom-transition" width="auto" class="px-5">
        <v-card :prepend-icon="prependIcon" class="px-5 py-3 main-dialog-card" :title="title">
            <form @submit.prevent="onComplete">
                <v-text-field 
                    v-model="email"
                    density="compact" 
                    placeholder="myBeatifulMail@gmail.com" 
                    label="Account"
                    prepend-inner-icon="mdi-email-outline" 
                    variant="outlined" 
                    class="my-2"
                    required
                    :rules="[emailValidation]"
                    ></v-text-field>

                <v-text-field 
                    v-model="pwd"
                    :append-inner-icon="isPwdVisibile ? 'mdi-eye-off' : 'mdi-eye'" 
                    label="Password"
                    :type="isPwdVisibile ? 'text' : 'password'" 
                    density="compact" 
                    placeholder="WhatAStrongPwd!"
                    prepend-inner-icon="mdi-lock-outline" 
                    variant="outlined"
                    @click:append-inner="isPwdVisibile = !isPwdVisibile"
                    required
                    :rules="[pwdValidation]"
                    ></v-text-field>

                <v-divider></v-divider>
                <v-card-actions>
                    <v-btn :text="$t('create_quiz_dialog_close_btn')"  @click="close"></v-btn>
                    <v-spacer></v-spacer>
                    <v-btn :text="$t('create_quiz_dialog_ok_btn')" type="submit" variant="outlined"></v-btn>
                </v-card-actions>
            </form>
        </v-card>
    </v-dialog>

</template>

<script lang="ts">
import type { AuthApi } from '@/api';
import i18n from '@/i18n/i18n';
import { Validators } from '@/utils/Validators';
import { defineComponent, inject, ref, type Ref } from 'vue';

export default defineComponent({
    props: {
        title: {
            type: String,
            required: true
        },
        prependIcon: {
            type: String,
            required: true
        }
    },
    setup() {
        const isPwdVisibile = ref(false);
        const showDialogState = ref(false);
        const authAPI = inject('AuthAPI') as AuthApi;
        const email: Ref<string | undefined> = ref(undefined);
        const pwd: Ref<string | undefined> = ref(undefined);


        function emailValidation(value: string){
            return Validators.email(value) || i18n.global.t('validation_email');
        }

        function pwdValidation(value: string){
            return Validators.password(value)|| i18n.global.t('validation_pwd');
        }

        function onComplete() {
            if(!pwd.value || !email.value || !Validators.email(email.value) || !Validators.password(pwd.value)){
                return;
            }
            
            authAPI.login({ customerDto: {
                password: pwd.value,
                username: email.value,
                isAnonymous: false
            }})
            .then(res => {
                console.log(res);
                close();
            })
            .catch(e => console.log(e));
        }

        function close(){
            showDialogState.value = !showDialogState.value;
            email.value = undefined;
            pwd.value = undefined;
        }

        return {
            isPwdVisibile,
            onComplete,
            showDialogState,
            email,
            pwd,
            emailValidation,
            pwdValidation,
            close
        }
    },
    components: {

    }
})
</script>
<style>
@media (max-width: 992px) {
    .main-dialog-card{
        min-width: 80vw;
    }
}
@media (min-width: 992px) {
    .main-dialog-card{
        min-width: 50vw;
    }
}
@media (min-width: 1200px) {
    .main-dialog-card{
        min-width: 40vw;
    }
}
@media (min-width: 1400px) {
    .main-dialog-card{
        min-width: 30vw;
    }
}

.v-card-item{
    padding: 1rem;
}

</style>