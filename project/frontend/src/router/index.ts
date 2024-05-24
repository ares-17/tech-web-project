import CreateQuiz from '@/views/CreateQuiz.vue';
import QuizQuestions from '@/views/QuizQuestions.vue';
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/views/HomeView.vue';

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
