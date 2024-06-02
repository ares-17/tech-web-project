import { createApp } from 'vue';
import 'bootstrap/dist/css/bootstrap.css';
import { createPinia } from 'pinia'
import 'bootstrap-icons/font/bootstrap-icons.css';

import App from './App.vue'
import router from './router';
import i18n from './i18n/i18n';
import { QuestionsApi, QuizApi, ScoreApi, UserApi, AuthApi } from './api';

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@fontawesome/fontawesome-free/css/all.css';

const app = createApp(App);

const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'fa'
    }
})

app.use(createPinia())
app.use(vuetify)
app.use(router)
app.use(i18n)

app.provide('QuestionsApi', new QuestionsApi());
app.provide('QuizApi', new QuizApi());
app.provide('UserApi', new UserApi());
app.provide('ScoreApi', new ScoreApi());
app.provide('AuthApi', new AuthApi());

app.mount('#app');

