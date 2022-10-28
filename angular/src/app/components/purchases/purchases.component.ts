import { Component, OnInit } from '@angular/core';
import {Order} from "../../models/order.model";
import {OrderService} from "../../services/order.service";
import {CartService} from "../../services/cart.service";
import {Cart} from "../../models/cart.model";

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrls: ['./purchases.component.css']
})
export class PurchasesComponent implements OnInit {

  cart: Cart | any;
  orders: Order[] | any= [];

  constructor(
    private cartService: CartService,
    private ordersService: OrderService
  ) { }

  ngOnInit(): void {
    this.cartService.myCart$.subscribe({
      next: data => this.cart = data
    })
    this.ordersService.myOrders$.subscribe({
      next: data => {
        if(this.cart) {
          const orders = data.filter( order => order.cart.cartId = this.cart.cartId );

          this.orders = orders.filter( order => order.status == 'shipped');
        }
      }
    })
  }

}
