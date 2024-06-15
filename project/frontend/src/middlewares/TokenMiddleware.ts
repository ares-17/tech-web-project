import { BASE_PATH, type Middleware } from "@/api/models/runtime";
import { useSessionStore } from "@/stores/sessionStore";

interface CSRFTokenResponse {
    token: string,
    headerName: string,
    parameterName: string
}
export default class TokenMiddleware implements Middleware {

    private sessionStore = useSessionStore();

    private async getCsrfToken(context: any): Promise<string>{
        const csrfToken = this.sessionStore.getFromSessionStorage('CSRF_TOKEN');

        if(csrfToken){
            return csrfToken;
        }
        const jsonBody: CSRFTokenResponse = await (await fetch(`${BASE_PATH}/csrf`)).json();
        this.sessionStore.saveToSessionStorage('CSRF_TOKEN', jsonBody.token);
        return jsonBody?.token;
    }

    async pre(context: any) {
        const sessionStorage = useSessionStore();
        const token = sessionStorage.getFromSessionStorage('token');
        if (token) {
            (context.init.headers as any)['Authorization'] = `Bearer ${token}`;
        }
        const csrf = await this.getCsrfToken(context);

        (context.init.headers as any)['X-XSRF-TOKEN'] = csrf;
        (context.init.headers as any)['X-CSRF-TOKEN'] = csrf;
        (context.init.headers as any)['X-Requested-With'] = 'XMLHttpRequest';
        (context.init.headers as any)['Content-Type'] = 'application/json';
        (context.init.headers as any)['Connection'] = 'keep-alive';
        (context.init.headers as any)['Accept-Encoding'] = 'gzip, deflate, br';
        (context.init.headers as any)['Accept'] = '*/*';
        (context.init.headers as any)['Host'] = 'localhost:8084';

        if(context.init.method === "POST"){
            const body = JSON.parse(context.init.body);
            body["_csrf"] = csrf;
            context.init.body = JSON.stringify(body);
        }

        return context;
    }
}