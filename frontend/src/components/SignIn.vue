<template>
    <div div="signin">
        <div class="login-form">
            <b-card
                    title="Login"
                    tag="article"
                    style="max-width: 20rem;"
                    class="mb-2"
            >
                <div>
                    <b-alert
                            :show="dismissCountDown"
                            dismissible
                            variant="danger"
                            @dismissed="dismissCountDown=0"
                            @dismiss-count-down="countDownChanged"
                    > {{ alertMessage }}
                    </b-alert>
                </div>
                <div>
                    <b-form-input type="text" placeholder="Login" v-model="userLogin" />
                    <div class="mt-2"></div>

                    <b-form-input type="password" placeholder="Password" v-model="password" />
                    <div class="mt-2"></div>
                </div>

                <b-button v-on:click="login" variant="primary">Login</b-button>

                <hr class="my-4" />

                <b-button variant="link">Forget password?</b-button>
            </b-card>
        </div>
    </div>
</template>

<script>
    import {AXIOS} from './http-commons'
    export default {
        name: 'SignIn',
        data() {
            return {
                userLogin: '',
                password: '',
                dismissSecs: 5,
                dismissCountDown: 0,
                alertMessage: 'Request error',
            }
        },
        methods: {
            login() {
                AXIOS.post(`/auth/signin`, {'login': this.$data.userLogin, 'password': this.$data.password})
                    .then(response => {
                        this.$store.dispatch('login', {'token': response.data.accessToken, 'roles': response.data.authorities, 'login': response.data.login});
                        this.$router.push('/home')
                    }, error => {
                        this.$data.alertMessage = (error.response.data.message.length < 150) ? error.response.data.message : 'Request error. Please, report this error website owners';
                        this.$data.error = error;
                    })
                    .catch(exception => {
                        this.$data.exception = exception;
                        this.showAlert();
                    })
            },
            countDownChanged(dismissCountDown) {
                this.dismissCountDown = dismissCountDown
            },
            showAlert() {
                this.dismissCountDown = this.dismissSecs
            },
        }
    }
</script>

<style>
    .login-form {
        margin-left: 38%;
        margin-top: 50px;
    }
</style>