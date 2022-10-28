import { Component, OnInit } from '@angular/core';
import {Customer} from "../../models/customer.model";
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customer: Customer;

  constructor( private customerService: CustomerService) {
    this.customer = customerService.customer;
  }

  ngOnInit(): void {
    const customerId = localStorage.getItem('MKPG');
    if (customerId) {
      this.customerService.getCustomerById(customerId)
        .subscribe(data => {
          this.customer = data;
        })
    }
  }

}
