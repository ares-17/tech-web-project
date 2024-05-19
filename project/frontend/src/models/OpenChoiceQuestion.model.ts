import Question from "./Question.model";

export default class OpenQuestion extends Question {
    constructor(
        public title: string,
        public answer: string,
    ){
        super(title, false);
    }
}