import { createApp } from 'vue';
import 'bootstrap/dist/css/bootstrap.css';
import { createPinia } from 'pinia'
import 'bootstrap-icons/font/bootstrap-icons.css';

import App from './App.vue'
import router from './router';
import i18n from './i18n/i18n';
import { QuestionsApi, QuizApi, ScoreApi, UserApi } from './api';



const app = createApp(App);
app.use(createPinia())
app.use(router)
app.use(i18n)

app.provide('QuestionsApi', new QuestionsApi());
app.provide('QuizApi', new QuizApi());
app.provide('UserApi', new UserApi());
app.provide('ScoreApi', new ScoreApi());

app.mount('#app');

