import { Injectable } from '@angular/core';
import { Item } from "../models/item.model";
import {BehaviorSubject} from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private _url: String = `http://localhost:8080/api/item`;
  private _itemArr: Item[] = [];

  private _item = new BehaviorSubject<Item[]>([]);

  items$ = this._item.asObservable();

  constructor(
    private http: HttpClient,
  ) {}



  setItem(item: Item[]){
    this._itemArr = item;
    this._item.next(this._itemArr);
  }

  getItem(item: Item){
    return item;
  }

  getAllItems() {
    return this.http.get<Item[]>(`${this._url}/all`);
  }

  getItemByName(itemName: String){
    return this.http.get<Item>(`${this._url}/name/${itemName}`);
  }

  getItemById(id: string){
    return this.http.get<Item>(`${this._url}/id/${id}`);
  }


}
