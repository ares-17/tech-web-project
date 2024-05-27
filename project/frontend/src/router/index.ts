import CreateQuiz from '@/views/CreateQuiz.vue';
import QuizQuestions from '@/views/QuizQuestions.vue';
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import LoginView from '@/views/LoginView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior: () => {
    return { el: '#app', behavior: 'smooth' }
  },
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/create-quiz',
      name: 'create-quiz',
      component: CreateQuiz
    },
    {
      path: "/quiz/:code",
      name: 'quiz-istance',
      props: true,
      component: QuizQuestions,
    },
  ]
});

export default router;
