import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


import { CategorieService } from './../../../services/categorie.service';
import { AuthenticationService } from './../../../services/authentication.service';

import { Examen } from './../../../models/model.examen';

@Component({
  selector: 'app-category-update',
  templateUrl: './category-update.component.html',
  styleUrls: ['./category-update.component.css']
})
export class CategoryUpdateComponent implements OnInit {

  categorie: any;
 
  idcategorie: number;

  constructor(
    public catService: CategorieService,
    public activatedRoute: ActivatedRoute,
    public router: Router
  ) { 
     this.idcategorie = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    return this.catService.getONeCategory(this.idcategorie)
      .subscribe(data => {
        this.categorie = data;
      
      console.log(data)
      }, err => {
         console.log('err');
      })
  }


  updateCategory() {
    return this.catService.updateCategory(this.categorie)
      .subscribe(data => {
                this.router.navigateByUrl('categorys');
      }, err => {
        console.log(err)
      })
  }

}
