import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';



import { Chapitre } from './../../models/model.chapitre';
import { ChapitreService } from './../../services/chapitre.service';
import { AuthenticationService } from './../../services/authentication.service';

@Component({
  selector: 'app-chapitres',
  templateUrl: './chapitres.component.html',
  styleUrls: ['./chapitres.component.css']
})
export class ChapitresComponent implements OnInit {


  pageChapitres: any;
  libelle: string = "";
  size: number = 5;
  page: number = 0;
  pages: Array<number>;
  currentPage: number = 0;

  constructor(
    private chService: ChapitreService,
    public authService: AuthenticationService,
    private router: Router
  ) { }

 ngOnInit() {
    this.doSearch();
  }

  doSearch() {
    this.chService.getPageChapitres(this.libelle, this.size, this.currentPage)
      .subscribe(data => {
        console.log(data)
        this.pageChapitres = data;
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


  onEditChapitre(id: number) {
    this.router.navigate(['chapitre-update', id]);

  }
  onDeleteChapitre(c: Chapitre) {
    let confirm = window.confirm('Est vous sure ?')
    if (confirm == true) {
      return this.chService.deleteChapitre(c.id)
        .subscribe(data => {
          let index = this.pageChapitres.content.indexOf(c);
          this.pageChapitres.content.splice(index, 1);
        }, err => {

        })
    }

  }
}
