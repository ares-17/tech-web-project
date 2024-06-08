import type { Middleware, ResponseContext } from "@/api/models/runtime";

export default class LoaderMiddleware implements Middleware {
    counter = 0;

    async pre() {
        document.getElementById('main-loader')!.style.visibility = "visible";
        this.counter++;
    }

    async post(context: ResponseContext): Promise<void | Response> {
        this.counter--;
        if (this.counter === 0) {
            document.getElementById('main-loader')!.style.visibility = 'hidden';
        }
        return context.response;
    }
}
