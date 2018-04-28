
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Question } from './../models/model.question';
import { Reponse } from './../models/model.reponse';



@Injectable()
export class QuestionService {

    private host: string = 'http://localhost:8080/question';
    private hostresp: string = 'http://localhost:8080/reponse';

    private jwtToken: string = null;


    constructor(

        public http: HttpClient


    ) { }

    loadToken() {
        this.jwtToken = localStorage.getItem('token');
    }


    getQuestion() {

        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/list',
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

    getPageQuestions(enonce: string, size: number, page: number) {
        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/page?enonce=' + enonce + "&size=" + size + "&page=" + page,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    saveQuestion(ques: Question) {

        return this.http.post(this.host + '/create', ques,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

    saveReponse(resp: Reponse) {

        return this.http.post(this.host + '/create', resp,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

    updateQuestion(ques: Question) {
        return this.http.put(this.host + '/update/' + ques.id, ques,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    deleteQuestion(id: number) {
        return this.http.delete(this.host + '/delete/' + id,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    getONeQuestion(id: number) {
        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/get/' + id,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

}