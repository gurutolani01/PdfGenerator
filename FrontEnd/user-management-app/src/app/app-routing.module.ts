// app-routing.module.ts

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserComponent } from './add-user/add-user.component';
import { SearchUserComponent } from './search-user/search-user.component';

const routes: Routes = [
  { path: '', redirectTo: '/add-user', pathMatch: 'full' },
  { path: 'add-user', component: AddUserComponent },
  { path: 'search-user', component: SearchUserComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

