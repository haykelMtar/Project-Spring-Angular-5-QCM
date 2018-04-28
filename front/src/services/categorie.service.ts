
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Categorie } from './../models/model.categorie';


@Injectable()
export class CategorieService {

    private host: string = 'http://localhost:8080/categorie';

    private jwtToken: string = null;


    constructor(

        public http: HttpClient


    ) { }

    loadToken() {
        this.jwtToken = localStorage.getItem('token');
    }


    getCategory() {

        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/list',
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

    getPageCategorys(libelle: string, size: number, page: number) {
        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/page?libelle=' + libelle + "&size=" + size + "&page=" + page,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    saveCategory(cat: Categorie) {

        return this.http.post(this.host + '/create', cat,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

    updateCategory(cat: Categorie) {
        return this.http.put(this.host + '/update/' + cat.id, cat,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    deleteCategory(id: number) {
        return this.http.delete(this.host + '/delete/' + id,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });
    }

    getONeCategory(id: number) {
        if (this.jwtToken == null) this.loadToken();
        return this.http.get(this.host + '/get/' + id,
            { headers: new HttpHeaders({ 'Authorization': this.jwtToken }) });

    }

}