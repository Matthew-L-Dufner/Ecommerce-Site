import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {createCustomerDTO, Customer} from "../models/customer.model";
import {Address, createAddressDTO} from "../models/address.model";
import {BehaviorSubject} from "rxjs";
import {CustomerService} from "./customer.service";
import {animate} from "@angular/animations";

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private _url: String = `http://localhost:8080/api/addresses`;

  private _addressesArr: Address[] = [];
  private _addresses = new BehaviorSubject<Address[]>([]);

  addresses$ = this._addresses.asObservable();

  constructor(
    private http: HttpClient,
    private customerService: CustomerService
  ) { }

  addAddresses(address: Address) {
    this._addressesArr.push(address);
    this._addresses.next(this._addressesArr);
  }


  setAddressesArr(value: Address[]) {
    this._addressesArr = value;
  }

  getAddressById(id: String) {
    return this.http.get<Address>(`${this._url}/${id}`);
  }

  createAddress(address: createAddressDTO) {
    return this.http.post<Address>( `${this._url}`, address);
  }
}
