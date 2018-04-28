import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { User } from './../../models/model.user';
import { UserService } from './../../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  mode: number = 1;

  constructor(public userService: UserService,
    public router: Router) { }

  ngOnInit() {
  }

  onRegister() {
    this.userService.regiter(this.user)
      .subscribe(data => {
        //this.user = data;
        this.router.navigateByUrl('/login');
      }, err => {
        console.log(err)
      })
  }

}
