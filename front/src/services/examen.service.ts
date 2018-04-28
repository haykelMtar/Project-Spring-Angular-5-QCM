

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Examen } from './../models/model.examen';


@Injectable()
export class ExamenService {

    private host: string = 'http://localhost:8080/examen';

    private jwtToken: string = null;


    constructor(
        public http: HttpClient
    ) { }

    loadToken() {
        this.jwtToken = localStorage.getItem('token');
    }

    getExams() {

        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/list',
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

    getPageExams(titre: string, size: number, page: number) {
        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/page?titre=' + titre + "&size=" + size + "&page=" + page,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    saveExam(exam: Examen) {

        return this.http.post(this.host + '/create', exam,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

    updateExam(exam: Examen) {
        return this.http.put(this.host + '/update/' + exam.id, exam,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    deleteExam(id: number) {
        return this.http.delete(this.host + '/delete/' + id,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    getONeExam(id: number) {
        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/get/' + id,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

}