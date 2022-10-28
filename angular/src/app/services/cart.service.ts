import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Cart, createCartDTO} from "../models/cart.model";
import {BehaviorSubject} from "rxjs";
import {Order} from "../models/order.model";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private _url: String = `http://localhost:8080/api/cart`;

  private cart: any;
  private myCart = new BehaviorSubject<any>(null);

  myCart$ = this.myCart.asObservable();
  constructor(
    private http: HttpClient
  ) {

  }

  getAllCarts() {
    return this.http.get<Cart[]>(`${this._url}`);
  }

  createCart(cart: createCartDTO) {
    return this.http.post<Cart>(`${this._url}`, cart);
  }

  setCart( cart: Cart ) {
    this.cart = cart;
    this.myCart.next(this.cart);
  }

  getCart() {
    return this.cart;
  }



}
