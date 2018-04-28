import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';



import { Question } from './../../models/model.question';
import { QuestionService } from './../../services/question.service';
import { AuthenticationService } from './../../services/authentication.service';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  pageQuestions: any;
  enonce: string = "";
  size: number = 5;
  page: number = 0;
  pages: Array<number>;
  currentPage: number = 0;

  constructor(
    public authService: AuthenticationService,
    private qService: QuestionService,
    private router: Router

  ) { }

  ngOnInit() {
    this.doSearch();
  }

  doSearch() {
    this.qService.getPageQuestions(this.enonce, this.size, this.currentPage)
      .subscribe(data => {
        console.log(data)
        this.pageQuestions = data;
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


  onDeleteExam(q: Question) {
    let confirm = window.confirm('Est vous sure ?')
    if (confirm == true) {
      return this.qService.deleteQuestion(q.id)
        .subscribe(data => {
          let index = this.pageQuestions.content.indexOf(q);
          this.pageQuestions.content.splice(index, 1);
        }, err => {

        })
    }

  }

}
