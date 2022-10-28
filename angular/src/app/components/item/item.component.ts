import {Component, Input, OnInit} from '@angular/core';
import {Item, orderItemDTO} from "../../models/item.model";
import {ItemService} from '../../services/item.service';
import {ActivatedRoute} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CartService} from "../../services/cart.service";
import {OrderService} from "../../services/order.service";
import {orderOrderDTO} from "../../models/order.model";

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  item: Item | any;
  cartId: number | any;

  form = new FormGroup({
    quantity: new FormControl("", [Validators.required])
  })

  constructor(
    private itemService: ItemService,
    private route: ActivatedRoute,
    private orderService:OrderService,
    private cartService:CartService
              ) {
  }


  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
      const id = params.get('id');
      this.itemService.getItemById(`${id}`).subscribe({
        next: data => {
          this.item = data;

          console.log(data)
        }
      })
    })

    this.cartService.myCart$.subscribe( data => {
      this.cartId = data.cartId;
    })
  }

  addToCart() {
    console.log(this.form.get('quantity')?.value)
    const order: orderOrderDTO = {
        quantity: `${this.form.get('quantity')?.value}`,
        deliveryDate: `${Date.now()}`,
        status: 'inCart',
        cart: {
          cartId: this.cartId,
          total: 0,
        },
        item: {
          itemId: `${this.item.itemId}`,
          itemName: '',
          price: 0,
          discountedPrice: 0,
        }

    }

    this.orderService.createOrder(order).subscribe(data => {
      this.orderService.addMyOrder(data);
    })


  }
}
