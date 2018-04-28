import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';


import { Examen } from './../../models/model.examen';
import { ExamenService } from './../../services/examen.service';
import { AuthenticationService } from './../../services/authentication.service';

import "rxjs/add/operator/map";

@Component({
  selector: 'app-exams',
  templateUrl: './exams.component.html',
  styleUrls: ['./exams.component.css']
})
export class ExamsComponent implements OnInit {

  pageExams: any;
  titre: string = "";
  size: number = 5;
  page: number = 0;
  pages: Array<number>;
  currentPage: number = 0;
  //totalPages :any = null ;




  constructor(
    public http: HttpClient,
    private exService: ExamenService,
    public authService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.doSearch();
  }

  doSearch() {
    this.exService.getPageExams(this.titre, this.size, this.currentPage)
      .subscribe(data => {
        console.log(data)
        this.pageExams = data;
        this.pages = new Array(data["totalPages"]);
      }, err => {
        console.log(err);
        this.authService.logout();
        this.router.navigateByUrl('/login')
      })
  }

  GoToPage(i: number) {
    this.currentPage = i;
    this.doSearch();

  }


  onEditExam(id: number) {
    this.router.navigate(['exam-update', id]);

  }
  onDeleteExam(e: Examen) {
    let confirm = window.confirm('Est vous sure ?')
    if (confirm == true) {
      return this.exService.deleteExam(e.id)
        .subscribe(data => {
          let index = this.pageExams.content.indexOf(e);
          this.pageExams.content.splice(index, 1);
        }, err => {

        })
    }

  }

}
