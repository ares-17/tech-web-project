export default abstract class Question {
    constructor(
        protected title: string,
        protected isMultipleChoices: boolean
    ){ }
}