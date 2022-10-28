import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../../services/customer.service";
import {Router} from "@angular/router";
import {FormGroup, FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  showErr: boolean = false;
  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  })

  constructor(
    private customerService: CustomerService,
    private router: Router) {
    if (localStorage.getItem('MKPG')) {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {
  }

  logIn() {
    if(this.form.valid){
      this.customerService.getCustomerByEmail(`${this.form.get('email')?.value}`)
        .subscribe({
          next: data => {
            this.showErr =false;
            if(data.password === this.form.get('password')?.value) {
              this.customerService.login(`${data.customerId}`);
              this.router.navigate(['/home']);
            }else {
              this.showErr =true;
            }
          },
          error: err => {
            this.showErr =true;
          }
      })
    }else {
      this.form.markAllAsTouched();
    }

  }

}
