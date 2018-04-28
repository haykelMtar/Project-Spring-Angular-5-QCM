import { Reponse } from './model.reponse'

export class Question {

    id: any = null;
    enonce: string = '';
    photo: string='';
    chapitre: any = null;

    reponses: Array<Reponse>;

    constructor() {

        this.reponses = new Array<Reponse>();

    }



}