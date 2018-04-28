import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './../models/model.user';

@Injectable()
export class UserService {

    private host: string = 'http://localhost:8080/user';

    constructor(
        public http: HttpClient
    ) { }


    regiter(user: User) {
        return this.http.post(this.host + '/register', user);
    }

}