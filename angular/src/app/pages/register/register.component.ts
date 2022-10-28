import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validator, Validators} from "@angular/forms";
import {createCustomerDTO} from "../../models/customer.model";
import {CustomerService} from "../../services/customer.service";
import {Route, Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form = new FormGroup({
    firstName: new FormControl('',[Validators.required]),
    lastName: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    phone: new FormControl('', [Validators.required]),
    birthDate: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {
    if (localStorage.getItem('MKPG')) {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {
  }

  getFirstName() {
    return this.form.get("firstName");
  }

  getObj(){
    console.log(this.form.get('firstName'))
  }

  save(){
    if(this.form.valid){
      const customer: createCustomerDTO = {
        firstName: `${this.form.get('firstName')?.value}`,
        lastName: `${this.form.get('lastName')?.value}`,
        email: `${this.form.get('email')?.value}`,
        password: `${this.form.get('password')?.value}`,
        phone: +`${this.form.get('phone')?.value}`,
        birthDate: +new Date(`${this.form.get('birthDate')?.value}`),
        created: Date.now(),
      }
      this.customerService.createCustomer( customer )
        .subscribe(data => {
          this.customerService.login(`${data.customerId}`);
          this.router.navigate(['/home']);
        })

    }else {
      this.form.markAllAsTouched();
    }
  }
}
