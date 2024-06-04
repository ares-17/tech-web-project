<template>
    <div class="row mx-auto">
        <div class="col-12 col-sm-12 col-md-12 col-lg-6 col-xl-4 mx-auto">
            <v-card class="px-5 py-3 main-dialog-card" :title="$t('dialog_login_title')">
                <form @submit.prevent="onComplete">
                    <v-text-field 
                        v-model="email"
                        density="compact" 
                        placeholder="myBeatifulMail@gmail.com" 
                        label="Account"
                        prepend-inner-icon="mdi-email-outline" 
                        variant="outlined" 
                        class="my-3"
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
                        <v-btn :text="$t('create_quiz_dialog_ok_btn')"
                            class="mx-auto" block
                            type="submit" variant="outlined"></v-btn>
                    </v-card-actions>
                </form>
            </v-card>
        </div>
    </div>

</template>

<script lang="ts">
import type { AuthApi } from '@/api';
import i18n from '@/i18n/i18n';
import { useUserStore } from '@/stores/userStore';
import { Validators } from '@/utils/Validators';
import { inject, ref, type Ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
    setup() {
        const isPwdVisibile = ref(false);
        const showDialogState = ref(false);
        const authAPI = inject('AuthApi') as AuthApi;
        const email: Ref<string | undefined> = ref(undefined);
        const pwd: Ref<string | undefined> = ref(undefined);
        const userStore = useUserStore();
        const router = useRouter();

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
                isAnonymous: false, // TODO
                isLogged: true // TODO
            }})
            .then(res => {
                userStore.setUser(res, email.value!);
                router.push('/');
            })
            .catch(e => console.log(e));
        }

        return {
            isPwdVisibile,
            onComplete,
            showDialogState,
            email,
            pwd,
            emailValidation,
            pwdValidation,
        }
    },
    components: {

    }
}
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