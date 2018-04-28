import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Categorie } from './../../../models/model.categorie';
import { CategorieService } from './../../../services/categorie.service';
import { AuthenticationService } from './../../../services/authentication.service';


@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.component.html',
  styleUrls: ['./category-create.component.css']
})
export class CategoryCreateComponent implements OnInit {

   categorie: Categorie = new Categorie();
   selectedCategorie: any;
   catrgories: any;


  constructor(
    public authService: AuthenticationService,
    public catService: CategorieService,
    public router: Router) { }

  ngOnInit() {
    this.chargerCat();
  }


  onChangeCategorie(categorie) {
    console.log(categorie)
    this.selectedCategorie = categorie;
    this.categorie.categSup = this.selectedCategorie;
    // utilisé this.selectedCategorie lorsque te passe les données au WS 
    console.log(this.selectedCategorie)
  }

  saveCategory() {
    return this.catService.saveCategory(this.categorie)
      .subscribe(data => {
        console.log(data)
        this.router.navigateByUrl('/categorys');
        
      }, err => {
        console.log(err)
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
