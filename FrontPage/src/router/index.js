import { createRouter, createWebHashHistory } from 'vue-router'
import home from '../views/home.vue'
import test from '../views/test.vue'
import rate from '../views/rate.vue'
import create_activity from "../views/create_activity.vue";
import course_rate from '../views/course_rate.vue'
import activity_rate from '../views/activity_rate.vue'
import show_course_rate from '../views/show_course_rate.vue'
import history from '../views/history.vue'
import login_page from "../views/login_page.vue";
import schedule from '../views/schedule.vue'


const routes = [
  {
    path: '/',
    component: home
  },
  {
    path: '/home',
    component: home
  },
  {
    path: '/test',
    component: test
  },
  {
    path: '/create_activity',
    component: create_activity
  },
  {
    path: '/rate',
    component: rate
  },
  {
    path: '/course_rate',
    component: course_rate
  },
  {
    path: '/activity_rate',
    component: activity_rate
  },
  {
    path: '/show_course_rate',
    component: show_course_rate
  },
  {
    path: '/history',
    component: history
  },
  {
    path: '/login_page',
    component: login_page
  },
  {
    path: '/schedule',
    component: schedule
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
