import Question from "./Question.model";

export default class MultipleChoiceQuestion extends Question{
    constructor(
        public title: string,
        public choices: string[],
        public correctChoice: number
    ){
        super(title, true);
    }
}