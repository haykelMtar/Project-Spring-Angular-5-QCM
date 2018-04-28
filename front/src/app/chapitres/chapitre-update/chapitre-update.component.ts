import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


import { ChapitreService } from './../../../services/chapitre.service';
import { AuthenticationService } from './../../../services/authentication.service';

import { Chapitre } from './../../../models/model.chapitre';

@Component({
  selector: 'app-chapitre-update',
  templateUrl: './chapitre-update.component.html',
  styleUrls: ['./chapitre-update.component.css']
})
export class ChapitreUpdateComponent implements OnInit {

  chapitre: any;
 
  idchapitre: number;

  constructor(
    public chService: ChapitreService,
    public activatedRoute: ActivatedRoute,
    public router: Router
  ) { 
     this.idchapitre= activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    return this.chService.getONeChapitre(this.idchapitre)
      .subscribe(data => {
        this.chapitre = data;
      
      console.log(data)
      }, err => {
         console.log('err');
      })
  }


  updateChapitre() {
    return this.chService.updateChapitre(this.chapitre)
      .subscribe(data => {
                this.router.navigateByUrl('chapitres');
      }, err => {
        console.log(err)
      })
  }

}
