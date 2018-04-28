import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './../../services/authentication.service'

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  username: any;

  constructor(
    public authService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    //this.username = this.authService.getCurrentUser().username;

  }

  onlogout() {
    console.log('logout')
    this.authService.logout();
    this.router.navigate(['/login']);

  }



}
