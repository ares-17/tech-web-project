<template>
    <div class="background" style="background-image: url('src/assets/background2.jpg');">
        <div class="container-fluid my-auto">
            <div class="row">
                <div class="col-12 col-sm-12 col-md-12 col-lg-8 col-xl-8 my-auto mx-auto">
                    <h1 class="my-auto mx-auto text-center title">SmartQuiz</h1>
                </div>
                <div class="col-12 col-sm-12 col-md-8 col-lg-4 col-xl-4 mx-auto">
                    <div class="input-container card shadow bg-body-tertiary">
                        <div class="card-body">
                            <label for="quizInput" class="form-label">{{ $t('logToQuiz_title') }}</label>
                            <div class="row">
                                <div class="col-9 col-md-9 col-sm-9 col-lg-9 col-xl-9 mb-2 ms-auto my-auto">
                                    <input type="text" class="form-control" id="quizInput" v-model="quizInput">
                                </div>
                                <div class="col-3 col-md-3 col-sm-3 col-lg-3 mb-2 col-xl-3 my-auto">
                                    <button class="btn btn-primary w-100"><i class="bi bi-arrow-right"></i></button>
                                </div>
                            </div>
                            <div class="separator mx-3">{{ $t('home_or_choice') }}</div>
                            <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12 mb-3 mx-auto my-3">
                                <button class="btn btn-primary w-100">{{ $t('home_create_new_quiz') }}</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
export default {
    name: 'HomeView',
    setup() {
        return {

        }
    },
    component: { }
};
</script>

<style scoped>
.background {
    height: 40vw;
    display: flex;
    background-size: cover;
    background-position: center;
}

@media (max-width: 1024px) {
    .background {
        height: 100vh;
        display: flex;
        background-size: cover;
        background-position: center;
    }
}

.title {
    font-family: Open Sans;
    font-size: 4rem;
    font-variant: petite-caps;
    color: whitesmoke;
}

.separator {
    display: flex;
    align-items: center;
    text-align: center;
}

.separator::before,
.separator::after {
    content: '';
    flex: 1;
    border-bottom: 1px solid #909090;
    opacity: 40%;
}

.separator:not(:empty)::before {
    margin-right: .25em;
}

.separator:not(:empty)::after {
    margin-left: .25em;
}

div.input-container {
    background-color: rgba(255, 255, 255, 0.8) !important;
}
</style>