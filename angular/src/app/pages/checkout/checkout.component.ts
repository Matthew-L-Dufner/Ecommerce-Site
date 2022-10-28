import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomerService} from "../../services/customer.service";
import {Customer} from "../../models/customer.model";
import {OrderService} from "../../services/order.service";
import {Order, OrderU} from "../../models/order.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  orders: Order[] | any;
  total:number = 0;
  customer: Customer | any;
  form = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('',[Validators.required]),
    email: new FormControl('',[Validators.required]),
    street: new FormControl('',[Validators.required]),
    city: new FormControl('',[Validators.required]),
    state: new FormControl('',[Validators.required]),
    zipCode: new FormControl('',[Validators.required]),
    cardNumber: new FormControl('',[Validators.required]),
    dateExpiration: new FormControl('',[Validators.required]),

  })

  constructor(
    private customerServer: CustomerService,
    private orderService: OrderService,
    private router: Router
  ) {
    this.customerServer.getCustomerById(`${localStorage.getItem('MKPG')}`).subscribe({
      next: data => {
        this.customer = data;
        this.form.get('firstName')?.patchValue(`${data.firstName}`);
        this.form.get('lastName')?.patchValue(`${data.lastName}`);
        this.form.get('email')?.patchValue(`${data.email}`);
        if (this.customer.address.length > 0){
          console.log(this.customer.address[0]);
          this.form.get('street')?.patchValue(`${this.customer.address[0].street}`);
          this.form.get('city')?.patchValue(`${this.customer.address[0].city}`);
          this.form.get('state')?.patchValue(`${this.customer.address[0].state}`);
          this.form.get('state')?.patchValue(`${this.customer.address[0].state}`);
          this.form.get('zipCode')?.patchValue(`${this.customer.address[0].zipCode}`);
        }

      }
    })
  }

  ngOnInit(): void {
    this.orderService.myOrders$.subscribe({
      next: data => {
        const myOrders = data.filter(order => order.status != "shipped" );
        this.orders = myOrders;
        let total = 0;
        myOrders.forEach( order => {
          total += +order.quantity * order.item.price;
        })
        this.total = total;
      }
    })
  }

  submit(){
    if(this.form.valid){
      this.orders.forEach( (order : OrderU | any) => {
        const myOrder: Order | any = {
          orderId: order.orderId,
          quantity: order.quantity,
          deliveryDate: order.deliveryDate,
          status: "shipped",
          cart: order.cart,
          item: order.item,
        }


        this.orderService.updateOrder( myOrder )
          .subscribe(data => {
            this.orderService.updateMyOrder(myOrder);
          })
      })


      this.router.navigate(['/thanks']);

    }else {
      this.form.markAllAsTouched();
    }
  }

  deleteItem(id: number){
    this.orderService.deleteOrder(`${id}`).subscribe({
      next: _ => {
        this.orderService.deleteMyOrder(id);
      }
    })
  }

}

