import NewCreateQuiz from '@/views/NewCreateQuiz.vue';
import { createRouter, createWebHistory } from 'vue-router';
import LoginView from '@/views/LoginView.vue';
import QuizIstance from '@/views/QuizIstance.vue';
import NewHomePage from '@/views/NewHomePage.vue';
import { useUserStore } from '@/stores/userStore';

const authGuard = (to: any, from: any) => {
  const userStore = useUserStore();
  if(!userStore.isAuthenticated()){
    return '/login'
  }
}

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
      component: NewCreateQuiz,
      beforeEnter: authGuard
    },
    {
      path: '/quiz/:id',
      name: 'quiz-istance',
      component: QuizIstance,
    }
  ]
});

export default router;
