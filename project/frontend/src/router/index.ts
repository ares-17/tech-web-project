import { useSessionStore } from '@/stores/sessionStore';
import LoginView from '@/views/LoginView.vue';
import NewCreateQuiz from '@/views/NewCreateQuiz.vue';
import NewHomePage from '@/views/NewHomePage.vue';
import QuizIstance from '@/views/QuizIstance.vue';
import RegisterView from '@/views/RegisterView.vue';
import TakeQuiz from '@/views/TakeQuiz.vue';
import { createRouter, createWebHistory } from 'vue-router';

const authGuard = (to: any, from: any) => {
  const sessionStorage = useSessionStore();
  if(!sessionStorage.getFromSessionStorage('token') ||
      !sessionStorage.getFromSessionStorage('expiresIn')){
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
      path: '/register',
      name: 'register',
      component: RegisterView
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
      props: true,
      component: QuizIstance,
    },
    {
      path: '/take-quiz/:id',
      name: 'take-quiz',
      props: true,
      component: TakeQuiz
    }
  ]
});

export default router;
