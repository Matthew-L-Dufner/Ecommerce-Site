export interface Cart {
  cartId: number,
  total: string,
  checkOutDate: string,
  customer: {
    customerId: string,
  };
}

export interface createCartDTO extends Omit<Cart, 'cartId' | 'checkOutDate'> {

}
