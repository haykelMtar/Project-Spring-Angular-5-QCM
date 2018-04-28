import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


import { AuthenticationService } from './../../services/authentication.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  mode: number = 0;
  constructor(
    private authService: AuthenticationService,
    private router: Router) { }

  ngOnInit() {
    this.authService.logout();
  }

  onLogin(user) {
    this.authService.login(user)
      .subscribe(resp => {
        console.log('hello');
        let jwt = resp.headers.get('Authorization');
        //console.log(resp.headers.get('Authorization'));
        this.authService.saveToken(jwt);
        this.router.navigateByUrl('/menu')
      }, err => {
        this.mode = 1;
        console.log('err');
      });
  }


  onRegister() {
      this.router.navigateByUrl('/register')
  }

}
