import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


import { ExamenService } from './../../../services/examen.service';
import { Examen } from './../../../models/model.examen';


@Component({
  selector: 'app-exam-update',
  templateUrl: './exam-update.component.html',
  styleUrls: ['./exam-update.component.css']
})
export class ExamUpdateComponent implements OnInit {
  examen: any;
  mode: number = 1;
  idExam: number;


  constructor(
    public examService: ExamenService,
    public activatedRoute: ActivatedRoute,
    public router: Router) {

    this.idExam = activatedRoute.snapshot.params['id'];

  }


  ngOnInit() {
    return this.examService.getONeExam(this.idExam)
      .subscribe(data => {
        this.examen = data;
        console.log("==============>" + this.examen.titre + "<=====")
      }, err => {
        // console.log(err);
      })
  }

  updateExam() {
    return this.examService.updateExam(this.examen)
      .subscribe(data => {
        this.mode = 2;
        this.router.navigateByUrl('exams');
      }, err => {
        console.log(err);
      })
  }

}
