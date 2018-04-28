
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelper } from 'angular2-jwt';

@Injectable()
export class AuthenticationService {

    private host: string = 'http://localhost:8080';
    private roles: Array<any>;
    private jwtToken = localStorage.getItem('token');

    private currentUser: any;


    constructor(public http: HttpClient) { }

    login(user) {
        return this.http.post(this.host + '/login', user, { observe: 'response' });
    }


    saveToken(jwt: string) {
        this.jwtToken = jwt;
        localStorage.setItem('token', jwt);
        let jwtHelper = new JwtHelper();
        this.roles = jwtHelper.decodeToken(this.jwtToken).roles;
       
    }


    logout() {
        localStorage.removeItem('token');
    }

       loadToken() {
        this.jwtToken = localStorage.getItem('token');
    }


    isAdmin() {
        // for (let r of this.roles) {
        //     if (r.authority == 'ADMIN') return true;

        // }
        // return false;
    }
    getCurrentUser(): any {
       return  this.currentUser = JSON.parse(localStorage.getItem('token'));
    }

} 
