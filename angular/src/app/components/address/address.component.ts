import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Address, createAddressDTO} from "../../models/address.model";
import {AddressService} from "../../services/address.service";
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  @Input() addresses: Address[] = [];

  form = new FormGroup({
    street: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    state: new FormControl('', [Validators.required]),
    type: new FormControl(''),
    zipCode: new FormControl('', [Validators.required]),
  })

  constructor(
    private addressService: AddressService,
    private customerService: CustomerService
  ) {

  }

  ngOnInit(): void {

    this.addressService.addresses$.subscribe({
      next: addresses => {
        this.addresses = addresses
      }
    })
  }

  save(){
    if (this.form.valid) {
      const address: createAddressDTO = {
        street: `${this.form.get('street')?.value}`,
        city: `${this.form.get('city')?.value}`,
        state: `${this.form.get('state')?.value}`,
        zipCode: `${this.form.get('zipCode')?.value}`,
        type: `${this.form.get('type')?.value}`,
        customer: {
          customerId: `${localStorage.getItem('MKPG')}`,
        }
      }
      console.log(address);
      this.addressService.createAddress(address)
        .subscribe({
          next: data => this.addressService.addAddresses(data),
          error: err => console.log(err)
        })

    } else {
      this.form.markAllAsTouched();
    }
  }

}
