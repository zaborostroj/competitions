<template>
    <div div="userpage">
        <h2>{{ pageContent }}</h2>
    </div>
</template>

<script>
    import {AXIOS} from './http-commons'
    export default {
        name: 'UserPage',
        data() {
            return {
                pageContent: ''
            }
        },
        methods: {
            loadUserContent() {
                const header = {'Authorization': 'Bearer ' + this.$store.getters.getToken};
                AXIOS.get('/usercontent', { headers: header })
                    .then(response => {
                        this.$data.pageContent = response.data;
                    })
                    .catch(exception => {
                        // console.log('ERROR: ' + exception.response.data);
                        this.$data.exception = exception;
                    })
            }
        },
        mounted() {
            this.loadUserContent();
        }
    }
</script>

<style>
</style>