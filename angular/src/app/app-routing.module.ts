import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";

import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ProfileComponent } from './pages/profile/profile.component';
import {HomeComponent} from "./pages/home/home.component";
import {CheckoutComponent} from "./pages/checkout/checkout.component";
import {ThanksComponent} from "./pages/thanks/thanks.component";
import {NintendoComponent} from "././pages/nintendo/nintendo.component";
import {PlayStationComponent} from "./pages/playstation/playstation.component";
import {XboxComponent} from "./pages/xbox/xbox.component";
import { ComputerComponent } from "./pages/computer/computer.component";
import {ItemComponent} from "./components/item/item.component";

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'profile',
    component: ProfileComponent
  },
  {
    path: 'checkout',
    component: CheckoutComponent
  },
  {
    path: 'item',
    component: ItemComponent
  },
  {
    path: 'thanks',
    component: ThanksComponent
  },
  {
    path: 'category/nintendo',
    component: NintendoComponent
  },
  {
    path: 'category/playstation',
    component: PlayStationComponent
  },
  {
    path: 'category/xbox',
    component: XboxComponent
  },
  {
    path: 'category/pc',
    component: ComputerComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
