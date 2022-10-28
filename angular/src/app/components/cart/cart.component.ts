import {Component, Input, OnInit} from '@angular/core';
import {CartService} from "../../services/cart.service";
import {Cart} from "../../models/cart.model";
import {OrderService} from "../../services/order.service";
import {Order, orderOrderDTO} from "../../models/order.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  @Input() cart: Cart;
  orders: Order[] | any = [];

  constructor(
    private cartService: CartService,
    private orderService: OrderService,
    private router: Router,
  ) {
    this.cart = this.cartService.getCart();
    if (this.cart) {
      this.setOrder(this.cart);
    }
  }

  ngOnInit(): void {
    this.cart = this.cartService.getCart();


    this.cartService.myCart$.subscribe({
      next: cart => {
        if (cart) {
          this.cart = cart;
          this.setOrder(cart)
        }
      }
    });

    this.orderService.myOrders$.subscribe({
      next: data => {
        console.log( "cart orders: " + data);
        this.orders = data.filter( order => order.status != 'shipped');
      }
    })
  }


  setOrder(cart:Cart) {
    this.orderService.getAllOrders().subscribe({
      next: data => {
        // @ts-ignore
        const orders: Order[] = data.filter( order => {
          if (order.cart) {
            return order.cart.cartId == cart.cartId;
          }
        })

        this.orderService.setOrders(orders);
        this.orders = orders.filter(order => order.status != 'shipped');
      }
    })
  }

  deleteItem(id: number){
    this.orderService.deleteOrder(`${id}`).subscribe({
      next: _ => {
        this.orderService.deleteMyOrder(id);
      }
    })
  }

  addItem() {
    const order: orderOrderDTO = {
      quantity: "5",
      deliveryDate: "01-22-2022",
      status: "inCart",
      cart: {
        cartId: 3,
        total: 100,
      },
      item: {
        itemId: "11",
        itemName: "",
        price: 0,
        discountedPrice: 0,
      }
    }

    this.orderService.createOrder(order).subscribe({
      next: data => {
        this.orderService.addMyOrder(data);
      }
    });
  }

}
