import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router'
import { UserComponent } from './user/user.component';
import { StockComponent } from './stock/stock.component';

const router: Routes = [
  {
    path: 'user',
    component: UserComponent
  },
  {
    path: 'stock',
    component: StockComponent
  },
  {
    path: '',
    component: UserComponent
  },
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(router)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
