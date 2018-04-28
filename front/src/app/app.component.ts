import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';


import { AuthenticationService } from './../services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  
  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) { }


  ngOnInit() {

  


  }


  onlogout() {
    this.authService.logout();
    this.router.navigateByUrl('/login')
  }
}
