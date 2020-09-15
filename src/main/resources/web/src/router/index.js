import Vue from 'vue'
import VueRouter from 'vue-router'
import '../assets/css/global.css'
const Home = () => import(/* webpackChunkName: "Home" */ '../views/Home.vue')
const Review = () => import(/* webpackChunkName: "word" */ '../views/Review.vue')
const SearchAndTranslate = () => import(/* webpackChunkName: "find" */ '../views/SearchAndTranslate')
const Choose = () => import(/* webpackChunkName: "word" */ '../views/Choose.vue')
const Statistics = () => import(/* webpackChunkName: "word" */ '../views/Statistics.vue')
const Setting = () => import(/* webpackChunkName: "word" */ '../views/Setting.vue')
const Rank = () => import(/* webpackChunkName: "word" */ '../components/Rank.vue')
const LearnSituation = () => import(/* webpackChunkName: "word" */ '../components/LearnSituation.vue')
const Search = () => import(/* webpackChunkName: "find" */ '../components/Search.vue')
const Translate = () => import(/* webpackChunkName: "find" */ '../components/Translate.vue')
const Blank = () => import(/* webpackChunkName: "word" */ '../views/Blank.vue')
const Calendar = () => import(/* webpackChunkName: "word" */ '../components/Calendar.vue')
const NewWords = () => import(/* webpackChunkName: "find" */ '../components/NewWords.vue')
const daka = () => import(/* webpackChunkName: "word" */ '../components/daka.vue')
const Records = () => import(/* webpackChunkName: "word" */ '../components/Records.vue')
const ReView = () => import(/* webpackChunkName: "find" */ '../components/ReView.vue')
const CET4 = () => import(/* webpackChunkName: "find" */ '../components/CET4.vue')
const CET4Detail = () => import(/* webpackChunkName: "word" */ '../components/CET4Detail.vue')
const CET6 = () => import(/* webpackChunkName: "find" */ '../components/CET6.vue')
const CET6Detail = () => import(/* webpackChunkName: "word" */ '../components/CET6Detail.vue')
const Test = () => import(/* webpackChunkName: "word" */ '../components/Test.vue')
// import Home from '../views/Home'
// import Review from '../views/Review'
// import SearchAndTranslate from '../views/SearchAndTranslate'
// import Choose from '../views/Choose'
// import Statistics from '../views/Statistics'
// import Setting from '../views/Setting'
// import Rank from '../components/Rank'
// import LearnSituation from '../components/LearnSituation'
// import Search from '../components/Search'
// import Translate from '../components/Translate'
// import Blank from '../views/Blank'
// import NewWords from '../components/NewWords'
// import Calendar from '../components/Calendar'
// import daka from '../components/daka'
// import Records from '../components/Records'
// import ReView from '../components/ReView'
// import CET4 from '../components/CET4'
// import CET4Detail from '../components/CET4Detail'
// import CET6 from '../components/CET6'
// import CET6Detail from '../components/CET6Detail'
// import Test from '../components/Test'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/blank'
  },
  {
    path: '/blank',
    name: 'blank',
    component: Blank
  },
  {
    path: '/home',
    name: 'home',
    component: Home,
    children: [
      {
        path: '/review',
        name: 'review',
        component: Review
      },
      {
        path: '/searchandtranslate',
        name: 'searchandtranslate',
        component: SearchAndTranslate
      },
      {
        path: '/choose',
        name: 'choose',
        component: Choose
      },
      {
        path: '/statistics',
        name: 'statistics',
        component: Statistics,
        redirect: '/rank',
        children: [
          {
            path: '/rank',
            name: 'rank',
            component: Rank
          },
          {
            path: '/learnSituation',
            name: 'learnSituation',
            component: LearnSituation
          }
        ]
      },
      {
        path: '/setting',
        name: 'setting',
        component: Setting
      },
      {
        path: '/search',
        name: 'search',
        component: Search
      },
      {
        path: '/translate',
        name: 'translate',
        component: Translate
      },
      {
        path: '/newWords',
        name: 'newWords',
        component: NewWords
      },
      {
        path: '/calendar',
        name: 'calendar',
        component: Calendar
      },
      {
        path: '/daka',
        name: 'daka',
        component: daka
      },
      {
        path: '/records',
        name: 'records',
        component: Records
      },
      {
        path: '/fuxi',
        name: 'fuxi',
        component: ReView
      },
      {
        path: '/cet4',
        name: 'cet4',
        component: CET4
      },
      {
        path: '/cet4detail',
        name: 'cet4detail',
        component: CET4Detail
      },
      {
        path: '/cet6',
        name: 'cet6',
        component: CET6
      },
      {
        path: '/cet6detail',
        name: 'cet6detail',
        component: CET6Detail
      },
      {
        path: '/test',
        name: 'test',
        component: Test
      }
    ]
  }
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // }
]

const router = new VueRouter({
  routes
})

export default router
