<template>
    <div class="container mt-5 pt-5">
        <div class="card p-4 shadow">
            <div class="card-body">
                <h2 class="card-title text-center mb-4">Login</h2>
                <form>
                    <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto">
                        <label for="username" class="form-label">{{ $t('createquiz_titlequiz') }}</label>
                        <input type="text" class="form-control" id="username" v-model="request.username">
                        <div v-if="submitted && !Validators.minLength(request.username || '')">
                            <p class="text-danger">{{ $t('validators_minlength') }}</p>
                        </div>
                    </div>
                    <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto">
                        <label for="description" class="form-label">{{ $t('createquiz_description') }}</label>
                        <input type="password" class="form-control" v-model="request.password"></input>
                        <div v-if="submitted && !Validators.minLength(request.password || '')">
                            <p class="text-danger">{{ $t('validators_minlength') }}</p>
                        </div>
                    </div>
                    <div class="col-12 col-md-12 col-sm-12 col-lg-6 col-xl-6 mb-3 mx-auto">
                        <button type="button" class="btn btn-primary w-100" @click="handleLogin">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <Toast ref="toastService" />
</template>

<script lang="ts">
import type { AuthApi } from '@/api';
import type { CustomerDto } from '@/api/models';
import Toast from '@/components/Toast.vue';
import i18n from '@/i18n/i18n';
import { Validators } from '@/utils/Validators';
import { inject, ref, type Ref } from 'vue';

export default {
    name: 'LoginView',
    setup() {
        const request: Ref<Partial<CustomerDto>> = ref({
            isLogged: false,
            isAnonymous: false
        });
        const submitted = ref(false);
        const authAPI: AuthApi = inject('AuthApi')!;
        const toastService: Ref<typeof Toast | null> = ref(null);

        function handleLogin() {
            submitted.value = true;
            authAPI.login({ customerDto: request.value as CustomerDto })
                .then(response => {
                    toastService.value?.show(i18n.global.t('login_success', { username: request?.value?.username }));
                })
                .catch(_ => {
                    toastService.value?.showError(i18n.global.t('login_error'));
                });
        }

        return {
            request,
            handleLogin,
            submitted,
            Validators,
            toastService
        }
    },
    components: {
        Toast
    }
}
</script>

<style></style>