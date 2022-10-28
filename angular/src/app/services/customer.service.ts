import { Injectable } from '@angular/core';
import {createCustomerDTO, Customer} from "../models/customer.model";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private userID: any = localStorage.getItem('MKPG');
  private _url: String = `http://localhost:8080/api/customers`;
  private _userId = new BehaviorSubject<String | null>(localStorage.getItem('MKPG'));

  userId$ = this._userId.asObservable();

  private _customer: Customer = {
    customerId: null,
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    phone: 0,
    birthDate: Date.now(),
    created: Date.now(),
  };

  constructor(
    private http: HttpClient
  ) { }

  get customer(): Customer {
    return this._customer;
  }

  login(customerId: String) {
    localStorage.setItem( 'MKPG', `${customerId}`);
    this._userId.next( customerId );
  }

  logout(){
    localStorage.removeItem('MKPG');
    this._userId.next(null);
  }

  getCustomerById(id: String) {
    return this.http.get<Customer>(`${this._url}/${id}`);
  }

  getCustomerByEmail(email: String) {
    return this.http.get<Customer>(`${this._url}/email/${email}`)
  }

  createCustomer( customer: createCustomerDTO){
    return this.http.post<Customer>( `${this._url}`, customer);
  }

  updateCustomer(customer: Customer) {
    return this.http.put<Customer>( `${this._url}`, customer);
  }

}
