import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Examen } from './../../../models/model.examen';
import { ExamenService } from './../../../services/examen.service';
import { CategorieService } from './../../../services/categorie.service';

@Component({
  selector: 'app-exam-create',
  templateUrl: './exam-create.component.html',
  styleUrls: ['./exam-create.component.css']
})
export class ExamCreateComponent implements OnInit {


  examen: Examen = new Examen();
  // mode: number = 1;
  catrgories: any;

  selectedCategorie: any;


  constructor(
    public catService: CategorieService,
    public examService: ExamenService,
    public router: Router) { }

  ngOnInit() {
    this.chargerCat();

  }

  onChangeCategorie(categorie) {
    console.log(categorie)
    this.selectedCategorie = categorie;
    this.examen.categorie = this.selectedCategorie;
    // utilisé this.selectedCategorie lorsque te passe les données au WS 
    console.log(this.selectedCategorie)
  }
  saveExam() {

    return this.examService.saveExam(this.examen)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['exams']);
      }, err => {
        //console.log(err);
      })
  }



  chargerCat() {
    return this.catService.getCategory()
      .subscribe(data => {
        this.catrgories = data;
      }, err => {
        console.log(err)
      });
  }

}
