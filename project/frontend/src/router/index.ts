import NewCreateQuiz from '@/views/NewCreateQuiz.vue';
import QuizQuestions from '@/views/QuizQuestions.vue';
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';
import LoginView from '@/views/LoginView.vue';
import NewHomePage from '@/views/NewHomePage.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior: () => {
    return { el: '#app', behavior: 'smooth' }
  },
  routes: [
    {
      path: '/',
      name: 'home',
      component: NewHomePage
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/create-quiz',
      name: 'create-quiz',
      component: NewCreateQuiz
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
