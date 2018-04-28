import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

import { Chapitre } from './../../../models/model.chapitre';
import { ChapitreService } from './../../../services/chapitre.service';
import { CategorieService } from './../../../services/categorie.service';

@Component({
  selector: 'app-chapitre-create',
  templateUrl: './chapitre-create.component.html',
  styleUrls: ['./chapitre-create.component.css']
})
export class ChapitreCreateComponent implements OnInit {

  chapitre: Chapitre = new Chapitre();
  // mode: number = 1;
  catrgories: any;

  selectedCategorie: any;


  constructor(
    public catService: CategorieService,
    public chService: ChapitreService,
    public router: Router) { }

  ngOnInit() {
    this.chargerCat();

  }

  onChangeCategorie(categorie) {
    console.log(categorie)
    this.selectedCategorie = categorie; // utilisé this.selectedCategorie lorsque te passe les données au WS 
    console.log(this.selectedCategorie)
  }
  chargerCat() {
    return this.catService.getCategory()
      .subscribe(data => {
        this.catrgories = data;
      }, err => {
        console.log(err)
      });
  }
  saveChapitre() {

    return this.chService.saveChapitre(this.chapitre)
      .subscribe(data => {
        console.log(data);
        this.router.navigate(['chapitres']);
      }, err => {
        //console.log(err);
      })
  }

}