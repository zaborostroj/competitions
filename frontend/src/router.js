import Vue from 'vue'
import Router from 'vue-router'
import Home from './components/Home'
import SignIn from './components/SignIn'
import SignUp from './components/SignUp'
import UserPage from "./components/UserPage";
import AdminPage from "./components/AdminPage";

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home
        },
        {
            path: '/login',
            name: 'SignIn',
            component: SignIn
        },
        {
            path: '/register',
            name: 'SignUp',
            component: SignUp
        },
        {
            path: '/user',
            name: 'UserPage',
            component: UserPage
        },
        {
            path: '/admin',
            name: 'AdminPage',
            component: AdminPage
        }
    ]
})