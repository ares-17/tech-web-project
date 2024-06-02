import i18n from "@/i18n/i18n";

export class Validators {

    static minLength(value: string, min = 3) {
        return (value?.length > min);
    }

    static numRange(value: string, options: {  min: number, max: number} = { min: 0, max: Number.MAX_SAFE_INTEGER }) {
        try {
            const numvalue = Number(value);
            return (numvalue >= options.min && numvalue <= options.max);
        } catch (e) {
            console.log(e)
            return false;
        }
    }
}