import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

import { Question } from './../../../models/model.question';
import { Reponse } from './../../../models/model.reponse';

import { QuestionService } from './../../../services/question.service';
import { ChapitreService } from './../../../services/chapitre.service';



@Component({
  selector: 'app-question-create',
  templateUrl: './question-create.component.html',
  styleUrls: ['./question-create.component.css']
})
export class QuestionCreateComponent implements OnInit {
  mode: number = 1;
  private question: Question = new Question();
  private reponse: Reponse = new Reponse();

  selectedChapitre: any;
  chapitres: any;
  reponses: any;

  id : any ;



  form: FormGroup;

  constructor(
    private router: Router,
    private qService: QuestionService,
    private chService: ChapitreService) { }

  ngOnInit() {
    this.chargerChap();

  }

  onChangeChapitre(chapitre) {
    console.log(chapitre)
    this.selectedChapitre = chapitre;
    this.question.chapitre = this.selectedChapitre;
    console.log(this.selectedChapitre)
  }
  chargerChap() {
    return this.chService.getChapitre()
      .subscribe(data => {
        this.chapitres = data;
      }, err => {
        console.log(err)
      });
  }
  saveQestion() {
    this.mode = 2;

  }




  addAnswer(question) {
    console.log('add answer');
    //this.reponse.question=question;
    question.reponses.push(new Reponse());
    console.log(question);
    
    this.reponse.id = question.id ;
    console.log(this.reponse.id);
  
  }

  deleteAnswer(question, answer) {

    console.log('delete answer');
    question.reponses = question.reponses.filter(a => a !== answer);
  }


  updateQestion(){
     return this.qService.saveQuestion(this.question)
      .subscribe(data => {
        console.log(data);
       // this.question.reponses["id"]=this.question ;
        console.log(data);
      }, err => {
        //console.log(err);
      })
  }

  onFileChange(event) {
    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      let file = event.target.files[0];
      reader.readAsDataURL(file);
      reader.onload = () => {
        this.form.get('photoquestion').setValue({
          filename: file.name,
          filetype: file.type,
          value: reader.result.split(',')[1]
        })
      };
    }
  }

}
