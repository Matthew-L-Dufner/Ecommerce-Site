export interface Item {
  itemId: number,
  itemName: string,
  description: string,
  price: number,
  discountedPrice: number,
  rating: string,
  itemImage: string,
  category: {
    categoryId: number,
    name: string,
    console: string
  }
}

export interface orderItemDTO extends Omit<Item, 'itemId'> {

}
