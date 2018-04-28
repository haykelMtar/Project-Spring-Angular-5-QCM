import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


import { Categorie } from './../../models/model.categorie';
import { CategorieService } from './../../services/categorie.service';
import { AuthenticationService } from './../../services/authentication.service';


@Component({
  selector: 'app-categorys',
  templateUrl: './categorys.component.html',
  styleUrls: ['./categorys.component.css']
})
export class CategorysComponent implements OnInit {

  pageCategorys: any;
  libelle: string = "";
  size: number = 5;
  page: number = 0;
  pages: Array<number>;
  currentPage: number = 0;

  constructor(
    private catService: CategorieService,
    public authService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit() {
    this.doSearch();
  }
  chercher(){
    return this.catService.getCategory()
      .subscribe(data=>
      this.pageCategorys=data)
  }

  doSearch() {
    this.catService.getPageCategorys(this.libelle, this.size, this.currentPage)
      .subscribe(data => {
        console.log(data)
        this.pageCategorys = data;
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


  onEditCategory(id: number) {
    this.router.navigate(['category-update', id]);

  }
  onDeleteCategory(c: Categorie) {
    let confirm = window.confirm('Est vous sure ?')
    if (confirm == true) {
      return this.catService.deleteCategory(c.id)
        .subscribe(data => {
          let index = this.pageCategorys.content.indexOf(c);
          this.pageCategorys.content.splice(index, 1);
        }, err => {

        })
    }

  }

}
