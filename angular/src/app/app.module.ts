import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CustomerComponent } from './components/customer/customer.component';
import { HeaderComponent } from './components/header/header.component';
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ProfileComponent } from './pages/profile/profile.component';
import {AppRoutingModule} from "./app-routing.module - Copy";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { HomeComponent } from './pages/home/home.component';
import { AddressComponent } from './components/address/address.component';
import { CartComponent } from './components/cart/cart.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { ThanksComponent } from './pages/thanks/thanks.component';
import { PurchasesComponent } from './components/purchases/purchases.component';
import { ItemComponent } from './components/item/item.component';
import { ComputerComponent } from './pages/computer/computer.component';
import { PlayStationComponent } from './pages/playstation/playstation.component';
import { XboxComponent } from './pages/xbox/xbox.component';
import { NintendoComponent } from './pages/nintendo/nintendo.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    HeaderComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    HomeComponent,
    AddressComponent,
    CartComponent,
    CheckoutComponent,
    ThanksComponent,
    PurchasesComponent,
    ItemComponent,
    ComputerComponent,
    PlayStationComponent,
    XboxComponent,
    NintendoComponent
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        ReactiveFormsModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
