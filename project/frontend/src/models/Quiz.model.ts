import type Question from "./Question.model";
import type User from "./User.model";

export default class Quiz {
    constructor(
        public title: string,
        public description: string,
        public createdAt: string,
        public uid: string,
        public createdBy: User,
        public questions: Array<Question>,
    ){ }
}