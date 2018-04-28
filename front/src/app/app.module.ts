import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { ExamCreateComponent } from './exams/exam-create/exam-create.component';
import { ExamUpdateComponent } from './exams/exam-update/exam-update.component';

import { AuthenticationService } from './../services/authentication.service';
import { ExamenService } from './../services/examen.service';
import { CategorieService } from './../services/categorie.service';
import { ChapitreService } from './../services/chapitre.service';
import { QuestionService } from './../services/question.service';


import { UserService } from './../services/user.service';



import { ExamsComponent } from './exams/exams.component';
import { RegisterComponent } from './register/register.component';
import { CategorysComponent } from './categorys/categorys.component';
import { CategoryCreateComponent } from './categorys/category-create/category-create.component';
import { CategoryUpdateComponent } from './categorys/category-update/category-update.component';
import { MenuComponent } from './menu/menu.component';
import { QuestionsComponent } from './questions/questions.component';
import { QuestionCreateComponent } from './questions/question-create/question-create.component';
import { ChapitresComponent } from './chapitres/chapitres.component';
import { ChapitreCreateComponent } from './chapitres/chapitre-create/chapitre-create.component';
import { ChapitreUpdateComponent } from './chapitres/chapitre-update/chapitre-update.component';

const appRoutes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  { path: "menu", component: MenuComponent },
  
  { path: "exam-create", component: ExamCreateComponent },
  { path: "exam-update/:id", component: ExamUpdateComponent },
  { path: "exams", component: ExamsComponent },

  { path: "category-create", component: CategoryCreateComponent },
  { path: "category-update/:id", component: CategoryUpdateComponent },
  { path: "categorys", component: CategorysComponent },

  { path: "chapitre-create", component: ChapitreCreateComponent },
  { path: "chapitre-update/:id", component: ChapitreUpdateComponent },
  { path: "chapitres", component: ChapitresComponent},

  { path: "question-create", component: QuestionCreateComponent },
  { path: "questions", component: QuestionsComponent},

  // { path: "index", component: AppComponent },


  { path: "", redirectTo: "/login", pathMatch: "full" }


];


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ExamCreateComponent,
    ExamUpdateComponent,
    ExamsComponent,
    RegisterComponent,
    CategorysComponent,
    CategoryCreateComponent,
    CategoryUpdateComponent,
    MenuComponent,
    QuestionsComponent,
    QuestionCreateComponent,
    ChapitresComponent,
    ChapitreCreateComponent,
    ChapitreUpdateComponent
    
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoutes), FormsModule, HttpClientModule
  ],
  providers: [AuthenticationService, ExamenService, CategorieService,ChapitreService,QuestionService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }