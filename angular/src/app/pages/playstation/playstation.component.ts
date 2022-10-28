import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { Item } from 'src/app/models/item.model';
import { ItemService } from '../../services/item.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-playstation',
  templateUrl: './playstation.component.html',
  styleUrls: ['./playstation.component.css']
})
export class PlayStationComponent implements OnInit {
  items: Item[] = [];
  router: Router;

  form = new FormGroup({
    itemName: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    price: new FormControl('', [Validators.required]),
    discountedPrice: new FormControl(''),
    rating: new FormControl('', [Validators.required]),
    itemImage: new FormControl('', [Validators.required]),
    category: new FormControl('', [Validators.required])
  })


  constructor(private itemService: ItemService,
    private _router: Router) {
      this.itemService.getAllItems().subscribe({
        next: data => {
          console.log(data);
          this.itemService.setItem(data);
        },
        error: data => console.log(data)
      });
      this.router = _router;
   }


  ngOnInit(): void {
    this.itemService.items$.subscribe({
      next: data => this.items = data
    })
  }

  isPlay(items: any[]): any[] {
    console.log(items.filter(item => item.category.console === "Playstation")
    )
    return items.filter(item => item.category.console === "Playstation")
  }

  itemPage(data: number){
    localStorage.setItem(`${data}.format`, 'itemID')
    this.router.navigate(['/item']);
    console.log(data);
  }
}
