import {Customer} from "./customer.model";

export interface Address {
  addressId: number,
  street: string,
  city: string,
  state: string,
  zipCode: string,
  type: string,
  customer: {
    customerId: string,
  };
}

export interface createAddressDTO extends Omit<Address, 'addressId'> {

}
