
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Chapitre } from './../models/model.chapitre';


@Injectable()
export class ChapitreService {

    private host: string = 'http://localhost:8080/chapitre';

    private jwtToken: string = null;


    constructor(

        public http: HttpClient


    ) { }

    loadToken() {
        this.jwtToken = localStorage.getItem('token');
    }


    getChapitre() {

        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/list',
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

    getPageChapitres(libelle: string, size: number, page: number) {
        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/page?libelle=' + libelle + "&size=" + size + "&page=" + page,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    saveChapitre(ch: Chapitre) {

        return this.http.post(this.host + '/create', ch,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

    updateChapitre(ch: Chapitre) {
        return this.http.put(this.host + '/update/' + ch.id, ch,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    deleteChapitre(id: number) {
        return this.http.delete(this.host + '/delete/' + id,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    getONeChapitre(id: number) {
        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/get/' + id,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

}