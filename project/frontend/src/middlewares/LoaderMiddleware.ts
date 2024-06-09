import type { Middleware, ResponseContext } from "@/api/models/runtime";
import { useErrorHandling } from "@/stores/errorHandling";

export default class LoaderMiddleware implements Middleware {
    counter = 0;
    readonly errorHandling = useErrorHandling();

    async pre() {
        document.getElementById('main-loader')!.style.visibility = "visible";
        this.counter++;
    }

    async post(context: ResponseContext): Promise<void | Response> {
        this.counter--;
        if (this.counter === 0) {
            document.getElementById('main-loader')!.style.visibility = 'hidden';
        }
        if(context.response.status !== 200){
            this.errorHandling.errorsSubject
                .next({ 
                    display: true, 
                    text: `STATUS_CODE: ${context.response.status}`
                });
        }

        return context.response;
    }
}
