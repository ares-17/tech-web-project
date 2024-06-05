<template>
    <div class="row">
        <div class="ps-5 col-12 col-10 col-sm-10 col-md-10 col-lg-6 col-xl-6 mx-auto mt-3 order-md-first order-sm-first">
            <v-card class="text-bg-light p-5 w-100" elevation="16">
                <div class="input-container bg-transparent w-100">
                    <div class="pb-5">
                        <div class="row">
                            <div class="col-12 col-md-6 col-sm-12 col-lg-6 col-xl-6 mb-2">
                                <v-btn rounded="md" @click="goBack" color="primary">
                                    <v-icon icon="mdi-arrow-left" class="mx-3"></v-icon>
                                    {{ $t('go_back_btn')}}
                                </v-btn>
                            </div>
                            <div class="d-flex justify-content-end col-12 col-md-6 col-sm-12 col-lg-6 col-xl-6 mb-2">
                                <v-btn rounded="md" aria-label="Generate qr code" @click="showQrCode" variant="outlined">
                                    {{ $t('quiz_deatails_qrcode_gen')}}
                                    <v-icon icon="mdi-qrcode" class="mx-3"></v-icon>
                                </v-btn>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                        <h1> {{ quizDetails?.title }} </h1>
                    </div>
                    <span> {{ quizDetails?.description }}</span>
                    <v-divider></v-divider>
                    <v-form readonly>
                        <div class="row">
                            <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-6 mb-2 h-100">
                                <v-text-field v-model="quizDetails!.numQuestions" label="First name"></v-text-field>
                            </div>
                            <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-6 mb-2 h-100">
                                <v-text-field v-model="quizDetails!.maxErrors" label="First name"></v-text-field>
                            </div>
                            <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-6 mb-2 h-100">
                                <v-text-field v-model="quizDetails!.createdBy" label="First name"></v-text-field>
                            </div>
                            <div class="col-12 col-md-12 col-sm-12 col-lg-12 col-xl-6 mb-2 h-100">
                                <v-text-field v-model="quizDetails!.createdAt" label="First name"></v-text-field>
                            </div>
                        </div>
                    </v-form>
                    <div>
                        <v-divider></v-divider>
                        <h4 class="align-items-center d-flex my-4"><v-icon icon="mdi-account"></v-icon> Punteggi ottenuti</h4>
                        <v-table max-height="300px" style="background: none;">
                            <thead>
                            <tr>
                                <th class="text-left">Nome utente</th>
                                <th class="text-left">Punteggio</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr v-for="(item, i) in scores" :key="i">
                                <td>{{ item.customerUsername }}</td>
                                <td>{{ item.score }}</td>
                            </tr>
                            </tbody>
                        </v-table>
                    </div>
                </div>
            </v-card>
        </div>
    </div>

    <v-dialog
      v-model="stateQrCodeDialog"
      width="auto">
      <v-card
        max-width="500"
        :text="$t('dialog_qrcode_text')"
        :title="$t('dialog_qrcode_title')">
        <div class="w-100 d-flex">
            <canvas ref="canvas" class="mx-auto"></canvas>
        </div>
        <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn :text="$t('create_quiz_dialog_ok_btn')" @click="stateQrCodeDialog = !stateQrCodeDialog"></v-btn>
      </v-card-actions>
      </v-card>
    </v-dialog>

</template>

<script lang="ts">
import type { QuizApi } from '@/api';
import type { QuizDto, ScoreDto } from '@/api/models';
import QRCode from 'qrcode';
import { inject, ref, type Ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
    props: {
        id: {
            type: String,
            required: true,
        },
    },
    setup(props) {
        const canvas = ref<HTMLCanvasElement | null>(null);
        const qrCodeGenerated = ref(false);
        const router = useRouter();
        const quizDetails: Ref<{ 
            title?: string,
            description?: string,
            numQuestions?: number,
            createdAt?: Date,
            maxErrors?: number,
            createdBy?: string
        }> = ref({});
        const quizApi = inject('QuizApi') as QuizApi;
        const stateQrCodeDialog = ref(false);

        quizDetails.value = {
            maxErrors: 1,
            numQuestions: 10,
            title: 'Primo quiz',
            description: "Prima descrizione",
            createdAt: new Date(),
            createdBy: 'Alessandro'
        };

        const scores: Ref<ScoreDto[]> = ref([{
            customerUsername: 'Alessandro',
            score: 10,
            customerId: '1212',
            quizId: ''
        }]);

        /*
        quizApi.getQuizById({ uidQuiz: props.id })
            .then(res => quiz.value = res)
            .catch(e => console.log(e));
        */

        function showQrCode(){
            stateQrCodeDialog.value = true;
            setTimeout(() => generateQrCode(), 100);
        }

        const getQrCodeSizeByDisplay = () => {
            const screenWidth = window.innerWidth;
            const screenHeight = window.innerHeight;
            const minDimension = Math.min(screenWidth, screenHeight);
            const qrCodeSize = Math.floor(0.5 * minDimension);
            return qrCodeSize;
        }

        const generateQrCode = () => {
            if (canvas.value) {
                const size = getQrCodeSizeByDisplay();
                const options = {
                    width: size,
                    height: size
                };
                const path = `localhost:5173//${props.id}`;
                QRCode.toCanvas(canvas.value, path, options, (error) => {
                    if (error) {
                        console.error(error);
                        qrCodeGenerated.value = false;
                    } else {
                        qrCodeGenerated.value = true;
                    }
                });
            }
        };

        function goBack(){
            router.push('/');
        }

        return {
            canvas,
            qrCodeGenerated,
            quizDetails,
            showQrCode,
            stateQrCodeDialog,
            scores,
            goBack
        };
    },
};
</script>

<style scoped>
@media (min-width: 992px) {
    .title-col {
        margin-left: 5rem;
    }

    .input-col {
        margin-left: 5rem;
    }

    h1.title {
        margin-top: 2rem;
        font-size: 4rem;
        color: white;
        font-weight: 600;
        font-family: Poppins;
        text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
    }
}

@media (max-width: 992px) {
    .title-col {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-left: 1rem;
    }

    .input-col {
        display: flex;
        justify-content: center;
        align-items: center;
        margin: auto;
    }

    h1.title {
        font-size: xx-large;
        color: white;
        font-weight: 600;
        font-family: Poppins;
        text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
    }
}

span.or {
    color: white;
    text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}

.v-table{
    background: none;
    border: 1px solid #999696;
    border-radius: 4px;
    padding: 1rem;
}
</style>