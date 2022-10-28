import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Order, orderOrderDTO, OrderU} from "../models/order.model";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private _url = "http://localhost:8080/api/orders"

  private orders: Order[] = [];
  private myOrders = new BehaviorSubject<Order[]>([]);

  myOrders$ = this.myOrders.asObservable();

  constructor(
    private http: HttpClient
  ) { }

  getAllOrders() {
    return this.http.get<Order[]>(`${this._url}`);
  }

  deleteOrder(id: string) {
    return this.http.delete(`${this._url}/${id}`);
  }

  createOrder(order:orderOrderDTO) {
    return this.http.post<Order>(`${this._url}`, order);
  }

  addMyOrder(order: Order) {
    this.orders.push(order);
    this.myOrders.next(this.orders);
  }

  updateMyOrder (data: Order) {
    const orderIndex = this.orders.findIndex( order => order.orderId === data.orderId);
    this.orders.splice(orderIndex, 1);
    this.orders.push(data);
    this.myOrders.next(this.orders);
  }

  deleteMyOrder(id: number) {
    const orderIndex = this.orders.findIndex( order => order.orderId === id);
    this.orders.splice(orderIndex, 1);
    this.myOrders.next(this.orders);
  }

  cleanMyOrder() {
    this.orders = [];
    this.myOrders.next(this.orders);
  }

  setOrders(orders: Order[]) {
    this.orders = orders;
    this.myOrders.next(this.orders);
  }

  getOrders() {
    return this.orders;
  }

  updateOrder( order: OrderU ) {
    return this.http.put(`${this._url}`, order);
  }


}
